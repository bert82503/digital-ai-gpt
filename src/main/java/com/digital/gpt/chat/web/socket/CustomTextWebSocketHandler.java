package com.digital.gpt.chat.web.socket;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.digital.gpt.chat.vo.req.OpenAiReplyResponse;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.PongMessage;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.concurrent.CountDownLatch;

/**
 * 文本处理器
 *
 * @author lihuagang
 * @date 2023/5/11
 * @see org.springframework.web.socket.WebSocketHandler
 * @see org.springframework.web.socket.handler.AbstractWebSocketHandler
 * @see org.springframework.web.socket.handler.TextWebSocketHandler
 */
@Slf4j
public class CustomTextWebSocketHandler extends TextWebSocketHandler {
    /**
     * 完成信号
     */
    private final CountDownLatch doneSignal;
    /**
     * 第三方身份，消息身份
     */
    private String thirdPartyId;
    /**
     * 回复消息内容
     */
    private String replyContent;
    private StringBuilder replyContentBuilder;
    // 异常场景
    /**
     * 匹配的敏感词
     */
    private String matchSensitiveWords;

    public CustomTextWebSocketHandler(CountDownLatch doneSignal) {
        this.doneSignal = doneSignal;
    }

    public String getThirdPartyId() {
        return thirdPartyId;
    }

    public String getReplyContent() {
        return replyContent;
    }

    public String getMatchSensitiveWords() {
        return matchSensitiveWords;
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        log.info("connection established, session={}", session);
        replyContentBuilder = new StringBuilder(16);
//        super.afterConnectionEstablished(session);
    }

    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
        super.handleMessage(session, message);
    }

    /**
     * 消息已接收完毕("stop")
     */
    private static final String MESSAGE_DONE = "[DONE]";

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
//        super.handleTextMessage(session, message);
        String payload = message.getPayload();
        log.info("payload={}", payload);
        OpenAiReplyResponse replyResponse = JSONUtil.toBean(payload, OpenAiReplyResponse.class);
        if (replyResponse != null && replyResponse.isSuccess()) {
            String msg = replyResponse.getMsg();
            if (StrUtil.isEmpty(msg)) {
                return;
            } else if (msg.startsWith("【超出最大单次回复字数】")) {
                // {"msg":"【超出最大单次回复字数】该提示由GPT官方返回，非我司限制，请缩减回复字数","code":1,
                // "extParam":"{\"chatId\":\"10056:8889007174\",\"requestId\":\"b6af5830a5a64fa8a4ca9451d7cb5f6f\",\"bizId\":\"\"}",
                // "id":"chatcmpl-7LThw6J9KmBUOcwK1SSOvdBP2vK9w"}
                return;
            } else if (msg.startsWith("发送内容包含敏感词")) {
                // {"msg":"发送内容包含敏感词，请修改后重试。不合规汇如下：炸弹","code":1,
                // "extParam":"{\"chatId\":\"10024:8889006970\",\"requestId\":\"828068d945c8415d8f32598ef6ef4ad6\",\"bizId\":\"430\"}",
                // "id":"4d4106c3-f7d4-4393-8cce-a32766d43f8b"}
                matchSensitiveWords = msg;
                // 请求完成
                doneSignal.countDown();
                return;
            } else if (MESSAGE_DONE.equals(msg)) {
                // 消息已接收完毕
                replyContent = replyContentBuilder.toString();
                thirdPartyId = replyResponse.getId();
                // 请求完成
                doneSignal.countDown();
                log.info("replyContent={}", replyContent);
                return;
            }
            replyContentBuilder.append(msg);
        }
    }

    @Override
    protected void handlePongMessage(WebSocketSession session, PongMessage message) throws Exception {
        super.handlePongMessage(session, message);
    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        replyContentBuilder = null;
        log.info("handle transport error, session={}", session, exception);
        doneSignal.countDown();
//        super.handleTransportError(session, exception);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        replyContentBuilder = null;
        log.info("connection closed, session={}, status={}", session, status);
        if (!CloseStatus.NORMAL.equals(status)) {
            log.error("connection closed fail, session={}, status={}", session, status);
        }
        doneSignal.countDown();
//        super.afterConnectionClosed(session, status);
    }
}
