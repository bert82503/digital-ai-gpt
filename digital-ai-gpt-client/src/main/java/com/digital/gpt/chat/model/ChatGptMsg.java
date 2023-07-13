package com.digital.gpt.chat.model;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 对话出参
 *
 * @author lihuagang
 * @date 2023/5/19
 */
@Data
@Accessors(chain = true)
public class ChatGptMsg implements Serializable {
    // 正常返回
    /**
     * 请求身份，可选
     */
    private String requestId;
    /**
     * 回复消息内容
     */
    private String replyContent;

    // 异常场景
    /**
     * 匹配的敏感词
     */
    private String matchSensitiveWords;

    private static final long serialVersionUID = 1L;
}
