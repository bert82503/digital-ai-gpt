package com.digital.gpt.chat.service.impl;

import com.digital.gpt.chat.common.config.OpenAiChatPromptProperties;
import com.digital.gpt.chat.common.enumerate.ModelTypeEnum;
import com.digital.gpt.chat.model.ChatGptMsg;
import com.digital.gpt.chat.model.ChatGptParam;
import com.digital.gpt.chat.openai.CompletionRsp;
import com.digital.gpt.chat.openai.OpenAiService;
import com.digital.gpt.chat.service.ChatService;
import com.digital.gpt.chat.service.GptService;
import com.digital.gpt.chat.vo.req.ChatCompletionReq;
import com.digital.gpt.chat.vo.rsp.ChatCompletionRsp;
import com.digital.gpt.chat.openai.CompletionReq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * 聊天对话模型服务实现
 *
 * @author lihuagang
 * @date 2023/5/14
 */
@Slf4j
@Configuration(proxyBeanMethods = false)
@EnableConfigurationProperties(OpenAiChatPromptProperties.class)
@Service("chatService")
public class ChatServiceImpl implements ChatService, GptService {

    private final OpenAiChatPromptProperties promptProperties;
    /**
     * OpenAI服务
     */
    private final OpenAiService<CompletionReq, CompletionRsp> openAiService;

    public ChatServiceImpl(
            OpenAiChatPromptProperties promptProperties,
            OpenAiService<CompletionReq, CompletionRsp> openAiService
    ) {
        this.promptProperties = promptProperties;
        this.openAiService = openAiService;
        log.info("create ChatServiceImpl instance");
    }

    private static CompletionReq applyCompletionReq(
            Long orgId, Long userId, ChatCompletionReq req, String prompt) {
        return new CompletionReq()
                .setOrgId(orgId)
                .setUserId(userId)
                .setRequestId(req.getRequestId())
                .setBizId(null)
                .setModelType(ModelTypeEnum.CHAT)
                .setPrompt(prompt)
                .setMessage(req.getMessage());
    }

    private static ChatCompletionRsp applyCompletionRsp(
            CompletionReq completionReq, CompletionRsp completionRsp) {
        return new ChatCompletionRsp()
                .setRequestId(completionReq.getRequestId())
                .setReplyContent(completionRsp.getReplyContent())
                .setMatchSensitiveWords(completionRsp.getMatchSensitiveWords());
    }

    @Override
    public ChatCompletionRsp completions(Long orgId, Long userId, ChatCompletionReq req) {
        CompletionReq completionReq = applyCompletionReq(orgId, userId, req, promptProperties.getRewritePolish());

        return Optional.of(completionReq)
                .map(openAiService::completions)
                .map(completionRsp -> applyCompletionRsp(completionReq, completionRsp))
                .orElse(null);
    }

    @Override
    public ChatGptMsg generateSummary(ChatGptParam param) {
        return doChatCompletion(promptProperties.getGenerateSummary(), param,
                openAiService::completions);
    }

    @Override
    public ChatGptMsg regenerateSummary(ChatGptParam param) {
        return doChatCompletion(promptProperties.getRegenerateSummary(), param,
                openAiService::completions);
    }

    @Override
    public ChatGptMsg translate(ChatGptParam param) {
        return doChatCompletion(promptProperties.getTranslate(), param,
                openAiService::completions);
    }
}
