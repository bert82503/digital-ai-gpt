package com.digital.gpt.chat.vo.req;

import lombok.Data;

/**
 * OpenAI回复消息
 * <p></p>
 * 注：每一条完整的消息，ws返回的消息身份id是一样的，开发者可以通过消息身份id来判断该消息流属于哪一条。
 * 且每一条完整的消息 都将会输出字符[DONE]，表示消息已接收完毕。
 *
 * @author lihuagang
 * @date 2023/5/10
 */
@Data
public class OpenAiReplyResponse {
    /**
     * 1-成功，0-失败
     */
    private static final int CODE_SUCCESS = 1;

    public boolean isSuccess() {
        return code == CODE_SUCCESS;
    }

    /**
     * 状态码
     * 1（成功），-5(账户字数已用完)
     */
    private int code;
    /**
     * API答复的消息内容
     */
    private String msg;
    /**
     * 消息身份
     */
    private String id;
//    /**
//     * 发消息时传入的json格式参数
//     */
//    private String extParam;
}
