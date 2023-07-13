package com.digital.gpt.chat.openai;

/**
 * OpenAI文本服务
 * <pre>
 * ## Completions
 * Given a prompt, the model will return one or more predicted completions,
 * and can also return the probabilities of alternative tokens at each position.
 *
 * 在给定提示语的情况下，模型将返回一个或多个预测的补全，并且还可以返回每个位置的替代令牌的概率。
 *
 * https://platform.openai.com/docs/api-reference/completions
 * </pre>
 *
 * @author lihuagang
 * @date 2023/5/14
 */
public interface OpenAiTextService {
    /**
     * 续写文本
     * <pre>
     * ### Create completion
     * Creates a completion for the provided prompt and parameters.
     *
     * 为提供的提示语和参数创建一个补全。
     *
     * https://platform.openai.com/docs/api-reference/completions/create
     * </pre>
     */
    void completions();
}
