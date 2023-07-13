package com.digital.gpt.chat.openai;

/**
 * OpenAI聊天对话服务
 * <pre>
 * ## Chat
 * Given a list of messages describing a conversation, the model will return a response.
 *
 * 给定一个描述对话的消息列表，模型将返回一个响应。
 *
 * https://platform.openai.com/docs/api-reference/chat
 * </pre>
 *
 * @author lihuagang
 * @date 2023/5/14
 */
public interface OpenAiChatService {
    /**
     * 润色聊天对话
     * <pre>
     * ### Create chat completion
     * Creates a model response for the given chat conversation.
     *
     * 为给定的聊天对话创建一个模型响应。
     *
     * https://platform.openai.com/docs/api-reference/chat/create
     * </pre>
     */
    void completions();
}
