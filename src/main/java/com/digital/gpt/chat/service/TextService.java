package com.digital.gpt.chat.service;

import com.digital.gpt.chat.vo.req.TextCompletionReq;
import com.digital.gpt.chat.vo.rsp.TextCompletionRsp;
import com.digital.gpt.chat.model.ChatGptMsg;
import com.digital.gpt.chat.model.ChatGptParam;

/**
 * 文本对话模型服务
 * <pre>
 * 业务场景：
 * 补全
 * 续写
 * 润色改写
 * 扩写
 * 帮写
 * 提取主题
 * 推理标题
 * 列出大纲
 * </pre>
 *
 * @author lihuagang
 * @date 2023/5/15
 */
public interface TextService {
    /**
     * 续写补全文本
     *
     * @param orgId  组织身份
     * @param userId 用户身份
     * @param req    请求参数
     * @return 回复消息内容
     */
    TextCompletionRsp completions(Long orgId, Long userId, TextCompletionReq req);

    // Completion 补全/文本接龙

    /**
     * 补全/续写一句话
     *
     * @param param 入参
     * @return 出参
     */
    ChatGptMsg continuousWrite(ChatGptParam param);

    /**
     * 润色改写
     *
     * @param param 入参
     * @return 出参
     */
    ChatGptMsg rewritePolish(ChatGptParam param);

    // expanding 扩写

    /**
     * 扩写一段话/几段话
     *
     * @param param 入参
     * @return 出参
     */
    ChatGptMsg expandWrite(ChatGptParam param);

    /**
     * 帮写一篇文章
     *
     * @param param 入参
     * @return 出参
     */
    ChatGptMsg helpWrite(ChatGptParam param);

    // inferring 推断/推理/提取主题

    /**
     * 提取主题
     *
     * @param param 入参
     * @return 出参
     */
    ChatGptMsg extractTopic(ChatGptParam param);

    /**
     * 推理标题
     *
     * @param param 入参
     * @return 出参
     */
    ChatGptMsg inferTitle(ChatGptParam param);

    /**
     * 列出大纲
     *
     * @param param 入参
     * @return 出参
     */
    ChatGptMsg listOutline(ChatGptParam param);
}
