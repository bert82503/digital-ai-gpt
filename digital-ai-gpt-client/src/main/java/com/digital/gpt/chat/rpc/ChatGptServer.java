package com.digital.gpt.chat.rpc;

import com.digital.common.tools.result.ApiResult;
import com.digital.rpc.protocol.AceMethod;
import com.digital.rpc.protocol.AceService;

import com.digital.gpt.chat.model.ChatGptMsg;
import com.digital.gpt.chat.model.ChatGptParam;

/**
 * 聊天对话模型补全服务
 * <pre>
 * Chat completions
 * Using the OpenAI Chat API, you can build your own applications with gpt-3.5-turbo and gpt-4 to do things like:
 * Draft an email or other piece of writing 起草电子邮件或书写其他文章
 * Write Python code
 * Answer questions about a set of documents 回答有关一组文档的问题
 * Create conversational agents 创建会话代理
 * Give your software a natural language interface 为您的软件提供自然语言界面
 * Tutor in a range of subjects 一系列主题的导师/指导
 * Translate languages 翻译语言
 * Simulate characters for video games and much more 模拟视频游戏的角色等等
 * Response format 响应格式
 * Managing tokens 管理字数
 * Instructing chat models 指导聊天模型
 * Chat vs Completions 聊天与补全/接龙
 * https://platform.openai.com/docs/guides/chat
 *
 * 业务场景：
 * 补全/接龙
 * 生成摘要
 * 翻译
 * </pre>
 *
 * @author lihuagang
 * @date 2023/5/18
 */
@SuppressWarnings("unused")
@AceService(proxy = "ChatGptServer", interfaceName = "ChatGptServer")
public interface ChatGptServer {
    /**
     * 生成摘要
     *
     * @param param 入参
     * @return 出参
     */
    @AceMethod(timeout = 300_000)
    ApiResult<ChatGptMsg> generateSummary(ChatGptParam param);

    /**
     * 重新生成摘要
     *
     * @param param 入参
     * @return 出参
     */
    @AceMethod(timeout = 300_000)
    ApiResult<ChatGptMsg> regenerateSummary(ChatGptParam param);

    /**
     * 翻译成不同的语言
     *
     * @param param 入参
     * @return 出参
     */
    @AceMethod(timeout = 300_000)
    ApiResult<ChatGptMsg> translate(ChatGptParam param);
}
