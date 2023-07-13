package com.digital.gpt.chat.service;

import com.digital.gpt.chat.model.ChatGptMsg;
import com.digital.gpt.chat.model.ChatGptParam;
import com.digital.gpt.chat.vo.req.ChatCompletionReq;
import com.digital.gpt.chat.vo.rsp.ChatCompletionRsp;

/**
 * 聊天对话模型服务
 * <pre>
 * 业务场景：
 * 补全
 * 润色
 * 生成摘要
 * 翻译
 * </pre>
 *
 * @author lihuagang
 * @date 2023/5/14
 */
public interface ChatService {
    // Completion 补全/文本接龙

    /**
     * 润色聊天对话
     *
     * @param orgId  组织身份
     * @param userId 用户身份
     * @param req    请求参数
     * @return 回复消息内容
     */
    ChatCompletionRsp completions(Long orgId, Long userId, ChatCompletionReq req);

    // Summarization 摘要

    /**
     * 生成摘要
     *
     * @param param 入参
     * @return 出参
     */
    ChatGptMsg generateSummary(ChatGptParam param);

    /**
     * 重新生成摘要
     *
     * @param param 入参
     * @return 出参
     */
    ChatGptMsg regenerateSummary(ChatGptParam param);

    // Transformation/transforming 转换

    /**
     * 翻译成不同的语言
     *
     * @param param 入参
     * @return 出参
     */
    ChatGptMsg translate(ChatGptParam param);
}
