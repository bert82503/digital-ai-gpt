package com.digital.gpt.chat.vo.req;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

/**
 * 续写文本对话入参
 *
 * @author lihuagang
 * @date 2023/5/14
 */
@Data
public class TextCompletionReq {
    /**
     * 请求身份，可选
     */
    private String requestId;
    /**
     * 一条文本提示语(a prompt)
     */
    @NotEmpty(message = "消息内容不能为空")
    @Size(max = 200, message = "消息内容长度不能超过200个字")
    private String message;
}
