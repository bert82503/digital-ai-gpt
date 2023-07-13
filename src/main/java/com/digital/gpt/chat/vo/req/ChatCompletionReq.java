package com.digital.gpt.chat.vo.req;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

/**
 * 润色聊天对话入参
 *
 * @author lihuagang
 * @date 2023/5/14
 */
@Data
public class ChatCompletionReq {
    /**
     * 请求身份，可选
     */
    private String requestId;
    /**
     * 聊天对话消息(a list of messages)
     */
    @NotEmpty(message = "消息内容不能为空")
    @Size(max = 500, message = "消息内容长度不能超过500个字符")
    private String message;
}
