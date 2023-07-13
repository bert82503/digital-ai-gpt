package com.digital.gpt.chat.service;

import cn.hutool.core.util.StrUtil;
import com.digital.gpt.chat.common.enumerate.ModelTypeEnum;
import com.digital.gpt.chat.model.ChatGptMsg;
import com.digital.gpt.chat.model.ChatGptParam;
import com.digital.gpt.chat.openai.CompletionRsp;
import com.digital.gpt.chat.openai.CompletionReq;

import java.util.Optional;
import java.util.function.Function;

/**
 * GPT模型服务
 *
 * @author lihuagang
 * @date 2023/5/31
 */
public interface GptService {

    /**
     * 应用指令参数
     *
     * @param promptFormat 提示模版
     * @param param        对话入参
     * @param modelType    模型类型
     * @return 指令入参
     */
    default CompletionReq applyCompletionReq(
            String promptFormat, ChatGptParam param, ModelTypeEnum modelType) {
        String prompt = promptFormat;
        int maxTokens = param.getMaxTokens();
        if (maxTokens > 0) {
            prompt = String.format(promptFormat, maxTokens);
        }
        String targetLang = param.getTargetLang();
        if (StrUtil.isNotEmpty(targetLang)) {
            prompt = String.format(promptFormat, targetLang);
        }
        String titleStyle = param.getTitleStyle();
        if (StrUtil.isNotEmpty(titleStyle)) {
            prompt = String.format(promptFormat, titleStyle);
        }
        return new CompletionReq()
                .setOrgId(param.getOrgId())
                .setUserId(param.getUserId())
                .setRequestId(param.getRequestId())
                .setBizId(param.getBizId())
                .setModelType(modelType)
                .setPrompt(prompt)
                .setMessage(param.getMessage())
                .setMaxTokens(maxTokens);
    }

    /**
     * 补全接龙文本对话
     *
     * @param promptFormat 提示模版
     * @param param        对话入参
     * @param function     服务方法
     * @return 对话出参
     */
    default ChatGptMsg doTextCompletion(
            String promptFormat, ChatGptParam param,
            Function<CompletionReq, CompletionRsp> function) {
        return doCompletion(promptFormat, param, ModelTypeEnum.TEXT, function);
    }

    /**
     * 补全接龙聊天对话
     *
     * @param promptFormat 提示模版
     * @param param        对话入参
     * @param function     服务方法
     * @return 对话出参
     */
    default ChatGptMsg doChatCompletion(
            String promptFormat, ChatGptParam param,
            Function<CompletionReq, CompletionRsp> function) {
        return doCompletion(promptFormat, param, ModelTypeEnum.CHAT, function);
    }

    /**
     * 补全接龙对话
     *
     * @param promptFormat 提示模版
     * @param param        对话入参
     * @param modelType    模型类型
     * @param function     服务方法
     * @return 对话出参
     */
    default ChatGptMsg doCompletion(
            String promptFormat, ChatGptParam param, ModelTypeEnum modelType,
            Function<CompletionReq, CompletionRsp> function) {
        CompletionReq req = applyCompletionReq(promptFormat, param, modelType);
        return Optional.of(req)
                .map(function)
                .map(rsp -> applyChatGptMsg(req, rsp))
                .orElse(null);
    }

    /**
     * 应用对话参数
     *
     * @param completionReq 指令入参
     * @param completionRsp 指令出参
     * @return 对话出参
     */
    default ChatGptMsg applyChatGptMsg(
            CompletionReq completionReq, CompletionRsp completionRsp) {
        return new ChatGptMsg()
                .setRequestId(completionReq.getRequestId())
                .setReplyContent(completionRsp.getReplyContent())
                .setMatchSensitiveWords(completionRsp.getMatchSensitiveWords());
    }
}
