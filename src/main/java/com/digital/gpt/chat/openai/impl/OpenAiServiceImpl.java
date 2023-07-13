package com.digital.gpt.chat.openai.impl;

import cn.hutool.json.JSONUtil;
import com.digital.common.tools.result.ApiResult;
import com.digital.gpt.chat.rpc.consumer.AntiSpamServer;
import com.digital.gpt.chat.common.util.UuidUtil;
import com.digital.gpt.chat.openai.CompletionRsp;
import com.digital.gpt.chat.openai.OpenAiService;
import com.digital.gpt.chat.openai.OpenAiUtil;
import com.digital.gpt.chat.service.ModelRequestRecordService;
import com.digital.gpt.chat.vo.req.OpenAiData;
import com.digital.gpt.chat.vo.req.OpenAiDataExtParam;
import com.digital.gpt.chat.vo.req.OpenAiParam;
import com.digital.gpt.chat.web.socket.CustomTextWebSocketHandler;
import com.digital.gpt.chat.web.socket.WebSocketUtil;
import com.digital.gpt.chat.common.config.OpenAiProperties;
import com.digital.gpt.chat.repository.entity.ModelRequestRecord;
import com.digital.gpt.chat.openai.CompletionReq;

import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.util.StopWatch;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.client.WebSocketClient;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * OpenAI服务实现
 *
 * @author lihuagang
 * @date 2023/5/18
 */
@Slf4j
@Configuration(proxyBeanMethods = false)
@EnableConfigurationProperties(OpenAiProperties.class)
@Service("openAiService")
public class OpenAiServiceImpl implements OpenAiService<CompletionReq, CompletionRsp> {

    private final OpenAiProperties properties;
    /**
     * 套接字客户端
     */
    private final WebSocketClient webSocketClient;
    /**
     * 反垃圾邮件服务
     */
    @Resource
    private AntiSpamServer antiSpamServer;
    /**
     * 模型请求记录服务
     */
    private final ModelRequestRecordService modelRequestRecordService;

    private static final String THREAD_NAME_PREFIX = "gpt.openai";

    public OpenAiServiceImpl(
            OpenAiProperties properties,
            ModelRequestRecordService modelRequestRecordService
    ) {
        this.properties = properties;
        this.modelRequestRecordService = modelRequestRecordService;
        webSocketClient = WebSocketUtil.applyWebSocketClient(THREAD_NAME_PREFIX);
        log.info("create OpenAiServiceImpl instance");
    }

    @Override
    public void beforeRequest(CompletionReq req) {
        // 请求身份
        if (StrUtil.isEmpty(req.getRequestId())) {
            req.setRequestId(UuidUtil.getUuid());
        }
    }

    @Override
    public void afterResponse(CompletionReq req, CompletionRsp rsp) {
        if (rsp == null || StrUtil.isEmpty(rsp.getReplyContent())) {
            return;
        }
        // 三方敏感词检测
        String matchSensitiveWords = rsp.getMatchSensitiveWords();
        if (StrUtil.isNotEmpty(matchSensitiveWords)) {
            // 敏感词命中
            rsp.setMatchSensitiveWords(matchSensitiveWords);
            return;
        }
        // 阶段任务耗时统计
        StopWatch stopWatch = new StopWatch(req.getRequestId());
        try {
            // 敏感词检测
            stopWatch.start("checkSensitiveWord");
            String replyContent = rsp.getReplyContent();
            ApiResult<String> apiResult = antiSpamServer.checkMsg(replyContent, false);
            stopWatch.stop();
            if (!apiResult.isSuccess() && StrUtil.isNotEmpty(apiResult.getData())) {
                // 敏感词命中
                rsp.setMatchSensitiveWords(apiResult.getData());
                return;
            }
            // 记录落库
            stopWatch.start("saveModelRequestRecord");
            ModelRequestRecord entity = applyModelRequestRecord(req, rsp);
            modelRequestRecordService.save(entity);
        } finally {
            if (stopWatch.isRunning()) {
                stopWatch.stop();
            }
            log.info("afterResponse execute time, {}", stopWatch);
        }
    }

    private static ModelRequestRecord applyModelRequestRecord(
            CompletionReq req, CompletionRsp rsp) {
        Long orgId = req.getOrgId();
        Long userId = req.getUserId();
        String chatId = applyChatId(orgId, userId);
        return new ModelRequestRecord()
                .setOrgId(orgId)
                .setUserId(userId)
                .setModelType(req.getModelType())
                .setRequestId(req.getRequestId())
                .setBizId(req.getBizId())
                .setChatId(chatId)
                .setThirdPartyId(rsp.getThirdPartyId())
                .setInputMessage(req.getMessage())
                .setReplyContent(rsp.getReplyContent());
    }

    private static String applyChatId(Long orgId, Long userId) {
        return orgId + ":" + userId;
    }

    private static String applySessionId(String appId, String chatId) {
        return appId + '_' + chatId;
    }

    private static final String URI_TEMPLATE = "wss://socket.yichenyufang3.com/websocket/{sessionId}";

    @Nullable
    @Override
    public CompletionRsp doCompletions(CompletionReq req) {
        // 阶段任务耗时统计
        StopWatch stopWatch = new StopWatch(req.getRequestId());
        stopWatch.start("doHandshake");

        // 闭锁，相当于一扇门(同步工具类)
        CountDownLatch doneSignal = new CountDownLatch(1);
        CustomTextWebSocketHandler webSocketHandler = new CustomTextWebSocketHandler(doneSignal);
        String chatId = applyChatId(req.getOrgId(), req.getUserId());
        String sessionId = applySessionId(properties.getAppId(), chatId);
        ListenableFuture<WebSocketSession> listenableFuture = webSocketClient
                .doHandshake(webSocketHandler, URI_TEMPLATE, sessionId);
        stopWatch.stop();
        stopWatch.start("getWebSocketSession");
        long connectionTimeout = properties.getConnectionTimeout().getSeconds();
        try (WebSocketSession webSocketSession = listenableFuture.get(connectionTimeout, TimeUnit.SECONDS)) {
            stopWatch.stop();
            stopWatch.start("sendMessage");
            OpenAiParam param = applyParam(chatId, req);
            webSocketSession.sendMessage(new TextMessage(JSONUtil.toJsonStr(param)));
            long requestTimeout = properties.getRequestTimeout().getSeconds();
            // wait for all to finish
            boolean await = doneSignal.await(requestTimeout, TimeUnit.SECONDS);
            if (!await) {
                log.error("await doneSignal fail, req={}", req);
            }
            String replyContent = webSocketHandler.getReplyContent();
            String matchSensitiveWords = webSocketHandler.getMatchSensitiveWords();
            if (StrUtil.isEmpty(replyContent) && StrUtil.isEmpty(matchSensitiveWords)) {
                // 消息回复异常
                return null;
            }
            String delimiters = properties.getDelimiters();
            replyContent = StrUtil.replaceFirst(replyContent, delimiters, "");
            replyContent = StrUtil.replaceLast(replyContent, delimiters, "");
            String thirdPartyId = webSocketHandler.getThirdPartyId();
            return new CompletionRsp()
                    .setThirdPartyId(thirdPartyId)
                    .setReplyContent(replyContent)
                    .setMatchSensitiveWords(matchSensitiveWords);
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            log.error("get WebSocketSession fail, req={}", req, e);
        } catch (IOException e) {
            log.error("sendMessage fail, req={}", req, e);
        } finally {
            if (stopWatch.isRunning()) {
                stopWatch.stop();
            }
            log.info("doCompletions execute time, {}", stopWatch);
        }
        return null;
    }

    private static final int MIN_TOKENS = 11;

    /**
     * 限制单次最大回复单词数(tokens)
     */
    private static int applyMaxTokens(int reqMaxTokens, int maxTokensConfig) {
        if (reqMaxTokens < MIN_TOKENS || maxTokensConfig < reqMaxTokens) {
            return maxTokensConfig;
        }
        return reqMaxTokens;
    }

    private OpenAiParam applyParam(String chatId, CompletionReq req) {
        OpenAiDataExtParam extParam = new OpenAiDataExtParam()
                .setChatId(chatId)
                .setRequestId(req.getRequestId())
                .setBizId(req.getBizId());
        // 提示
        String prompt = req.getPrompt();
        // 分隔符
        String delimiters = properties.getDelimiters();
        // 完整的输入
        String message = prompt + delimiters + req.getMessage() + delimiters;
        int maxTokens = applyMaxTokens(req.getMaxTokens(), properties.getMaxTokens());
        OpenAiData data = new OpenAiData()
                .setMsg(message)
                .setContext(properties.getContext())
                .setLimitTokens(maxTokens)
                .setExtParam(extParam);
        String sign = OpenAiUtil.applySign(message, properties.getSecret());
        return new OpenAiParam()
                .setData(data)
                .setSign(sign);
    }
}
