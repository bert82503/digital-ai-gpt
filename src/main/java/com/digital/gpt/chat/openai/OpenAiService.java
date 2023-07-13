package com.digital.gpt.chat.openai;

/**
 * OpenAI服务
 * <pre>
 * API reference introduction
 * https://platform.openai.com/docs/api-reference/introduction
 * </pre>
 *
 * @author lihuagang
 * @date 2023/5/18
 */
public interface OpenAiService<Req, Rsp> extends OpenAiHandler<Req, Rsp> {
    /**
     * 补全指令
     *
     * @param req 入参
     * @return 出参
     */
    default Rsp completions(Req req) {
        this.beforeRequest(req);
        Rsp rsp = this.doCompletions(req);
        this.afterResponse(req, rsp);
        return rsp;
    }

    /**
     * 操作补全指令
     *
     * @param req 入参
     * @return 出参
     */
    Rsp doCompletions(Req req);
}
