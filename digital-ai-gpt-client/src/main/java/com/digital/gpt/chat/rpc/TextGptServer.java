package com.digital.gpt.chat.rpc;

import com.digital.common.tools.result.ApiResult;
import com.digital.gpt.chat.model.ChatGptMsg;
import com.digital.gpt.chat.model.ChatGptParam;
import com.digital.rpc.protocol.AceMethod;
import com.digital.rpc.protocol.AceService;

/**
 * 文本对话模型补全服务
 * <pre>
 * Text completion
 * Learn how to generate or manipulate text
 * Prompt design 提示设计
 *   Classification 分类
 *   Generation 生成
 *   Conversation 对话
 *   Transformation 转换
 *   Translation 翻译
 *   Conversion 转换
 *   Summarization 摘要
 *   Completion 补全/文本接龙
 *   Factual responses 事实答复
 * Best practices 最佳实践
 *   Use max_tokens > 256.
 *   Prefer finish_reason == "stop".
 *   Resample 3-5 times.
 *   Try giving more clues. 尝试提供更多线索
 * https://platform.openai.com/docs/guides/completion
 *
 * 业务场景：
 * 续写
 * 润色改写
 * 扩写
 * </pre>
 *
 * @author lihuagang
 * @date 2023/5/30
 */
@SuppressWarnings("unused")
@AceService(proxy = "TextGptServer", interfaceName = "TextGptServer")
public interface TextGptServer {
    /**
     * 续写一句话
     *
     * @param param 入参
     * @return 出参
     */
    @AceMethod(timeout = 60_000)
    ApiResult<ChatGptMsg> continuousWrite(ChatGptParam param);

    /**
     * 润色改写
     *
     * @param param 入参
     * @return 出参
     */
    @AceMethod(timeout = 300_000)
    ApiResult<ChatGptMsg> rewritePolish(ChatGptParam param);

    /**
     * 扩写一段话/几段话
     *
     * @param param 入参
     * @return 出参
     */
    @AceMethod(timeout = 300_000)
    ApiResult<ChatGptMsg> expandWrite(ChatGptParam param);

    /**
     * 帮写一篇文章
     *
     * @param param 入参
     * @return 出参
     */
    @AceMethod(timeout = 300_000)
    ApiResult<ChatGptMsg> helpWrite(ChatGptParam param);

    /**
     * 提取主题
     *
     * @param param 入参
     * @return 出参
     */
    @AceMethod(timeout = 300_000)
    ApiResult<ChatGptMsg> extractTopic(ChatGptParam param);

    /**
     * 推理标题
     *
     * @param param 入参
     * @return 出参
     */
    @AceMethod(timeout = 60_000)
    ApiResult<ChatGptMsg> inferTitle(ChatGptParam param);

    /**
     * 列出大纲
     *
     * @param param 入参
     * @return 出参
     */
    @AceMethod(timeout = 300_000)
    ApiResult<ChatGptMsg> listOutline(ChatGptParam param);
}
