package com.digital.gpt.chat.rpc.provider;

import com.digital.common.tools.result.ApiResult;
import com.digital.gpt.chat.rpc.ChatGptServer;
import com.digital.gpt.chat.model.ChatGptMsg;
import com.digital.gpt.chat.model.ChatGptParam;
import com.digital.gpt.chat.service.ChatService;

import com.digital.rpc.protocol.AceProvider;
import lombok.extern.slf4j.Slf4j;

/**
 * 聊天对话模型补全服务提供者
 *
 * @author lihuagang
 * @date 2023/5/19
 */
@Slf4j
@AceProvider("chatGptServer")
public class ChatGptProvider extends GptProvider implements ChatGptServer {

    private final ChatService chatService;

    public ChatGptProvider(
            ChatService chatService
    ) {
        this.chatService = chatService;
        log.info("create ChatGptProvider instance");
    }

    @Override
    public ApiResult<ChatGptMsg> generateSummary(ChatGptParam param) {
        return applyGpt(param, chatService::generateSummary, "generateSummary");
    }

    @Override
    public ApiResult<ChatGptMsg> regenerateSummary(ChatGptParam param) {
        return applyGpt(param, chatService::regenerateSummary, "regenerateSummary");
    }

    @Override
    public ApiResult<ChatGptMsg> translate(ChatGptParam param) {
        return applyGpt(param, chatService::translate, "translate");
    }
}
