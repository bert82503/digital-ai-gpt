package com.digital.gpt.chat.openai;

/**
 * OpenAI处理器
 *
 * @author lihuagang
 * @date 2023/5/18
 */
public interface OpenAiHandler<Req, Rsp> {
    /**
     * 请求前置处理
     *
     * @param req 入参
     */
    default void beforeRequest(Req req) {
        //
    }

    /**
     * 响应后置处理
     *
     * @param req 入参
     * @param rsp 出参
     */
    default void afterResponse(Req req, Rsp rsp) {
        //
    }
}
