package com.digital.gpt.chat.service.impl;

import com.digital.gpt.chat.common.config.OpenAiTextPromptProperties;
import com.digital.gpt.chat.common.enumerate.ModelTypeEnum;
import com.digital.gpt.chat.openai.CompletionRsp;
import com.digital.gpt.chat.service.TextService;
import com.digital.gpt.chat.vo.req.TextCompletionReq;
import com.digital.gpt.chat.vo.rsp.TextCompletionRsp;
import com.digital.gpt.chat.model.ChatGptMsg;
import com.digital.gpt.chat.model.ChatGptParam;
import com.digital.gpt.chat.openai.CompletionReq;
import com.digital.gpt.chat.openai.OpenAiService;
import com.digital.gpt.chat.service.GptService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * 文本对话模型服务实现
 *
 * @author lihuagang
 * @date 2023/5/15
 */
@Slf4j
@Configuration(proxyBeanMethods = false)
@EnableConfigurationProperties(OpenAiTextPromptProperties.class)
@Service("textService")
public class TextServiceImpl implements TextService, GptService {

    private final OpenAiTextPromptProperties promptProperties;
    /**
     * OpenAI服务
     */
    private final OpenAiService<CompletionReq, CompletionRsp> openAiService;

    public TextServiceImpl(
            OpenAiTextPromptProperties promptProperties,
            OpenAiService<CompletionReq, CompletionRsp> openAiService
    ) {
        this.promptProperties = promptProperties;
        this.openAiService = openAiService;
        log.info("create TextServiceImpl instance");
    }

    private static CompletionReq applyCompletionReq(
            Long orgId, Long userId, TextCompletionReq req, String prompt) {
        return new CompletionReq()
                .setOrgId(orgId)
                .setUserId(userId)
                .setRequestId(req.getRequestId())
                .setBizId(null)
                .setModelType(ModelTypeEnum.TEXT)
                .setPrompt(prompt)
                .setMessage(req.getMessage());
    }

    private static TextCompletionRsp applyCompletionRsp(
            CompletionReq completionReq, CompletionRsp completionRsp) {
        return new TextCompletionRsp()
                .setRequestId(completionReq.getRequestId())
                .setReplyContent(completionRsp.getReplyContent())
                .setMatchSensitiveWords(completionRsp.getMatchSensitiveWords());
    }

    @Override
    public TextCompletionRsp completions(Long orgId, Long userId, TextCompletionReq req) {
        CompletionReq completionReq = applyCompletionReq(orgId, userId, req, promptProperties.getContinuousWrite());

        return Optional.of(completionReq)
                .map(openAiService::completions)
                .map(completionRsp -> applyCompletionRsp(completionReq, completionRsp))
                .orElse(null);
    }

    @Override
    public ChatGptMsg continuousWrite(ChatGptParam param) {
        return doTextCompletion(promptProperties.getContinuousWrite(), param,
                openAiService::completions);
    }

    @Override
    public ChatGptMsg rewritePolish(ChatGptParam param) {
        return doTextCompletion(promptProperties.getRewritePolish(), param,
                openAiService::completions);
    }

    @Override
    public ChatGptMsg expandWrite(ChatGptParam param) {
        return doTextCompletion(promptProperties.getExpandWrite(), param,
                openAiService::completions);
    }

    @Override
    public ChatGptMsg helpWrite(ChatGptParam param) {
        return doTextCompletion(promptProperties.getHelpWrite(), param,
                openAiService::completions);
    }

    @Override
    public ChatGptMsg extractTopic(ChatGptParam param) {
        return doTextCompletion(promptProperties.getExtractTopic(), param,
                openAiService::completions);
    }

    @Override
    public ChatGptMsg inferTitle(ChatGptParam param) {
        return doTextCompletion(promptProperties.getInferTitle(), param,
                openAiService::completions);
    }

    @Override
    public ChatGptMsg listOutline(ChatGptParam param) {
        return doTextCompletion(promptProperties.getListOutline(), param,
                openAiService::completions);
    }
}
