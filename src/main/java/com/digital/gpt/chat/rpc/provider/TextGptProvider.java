package com.digital.gpt.chat.rpc.provider;

import com.digital.common.tools.result.ApiResult;
import com.digital.gpt.chat.rpc.TextGptServer;
import com.digital.gpt.chat.service.TextService;
import com.digital.gpt.chat.model.ChatGptMsg;
import com.digital.gpt.chat.model.ChatGptParam;

import com.digital.rpc.protocol.AceProvider;
import lombok.extern.slf4j.Slf4j;

/**
 * 文本对话模型补全服务提供者
 *
 * @author lihuagang
 * @date 2023/5/30
 */
@Slf4j
@AceProvider("textGptServer")
public class TextGptProvider extends GptProvider implements TextGptServer {

    private final TextService textService;

    public TextGptProvider(
            TextService textService
    ) {
        this.textService = textService;
        log.info("create TextGptProvider instance");
    }

    @Override
    public ApiResult<ChatGptMsg> continuousWrite(ChatGptParam param) {
        return applyGpt(param, textService::continuousWrite, "continuousWrite");
    }

    @Override
    public ApiResult<ChatGptMsg> rewritePolish(ChatGptParam param) {
        return applyGpt(param, textService::rewritePolish, "rewritePolish");
    }

    @Override
    public ApiResult<ChatGptMsg> expandWrite(ChatGptParam param) {
        return applyGpt(param, textService::expandWrite, "expandWrite");
    }

    @Override
    public ApiResult<ChatGptMsg> helpWrite(ChatGptParam param) {
        return applyGpt(param, textService::helpWrite, "helpWrite");
    }

    @Override
    public ApiResult<ChatGptMsg> extractTopic(ChatGptParam param) {
        return applyGpt(param, textService::extractTopic, "extractTopic");
    }

    @Override
    public ApiResult<ChatGptMsg> inferTitle(ChatGptParam param) {
        return applyGpt(param, textService::inferTitle, "inferTitle");
    }

    @Override
    public ApiResult<ChatGptMsg> listOutline(ChatGptParam param) {
        return applyGpt(param, textService::listOutline, "listOutline");
    }
}
