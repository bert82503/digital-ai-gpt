package com.digital.gpt.chat.vo.rsp;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 续写文本对话出参
 *
 * @author lihuagang
 * @date 2023/5/14
 */
@Data
@Accessors(chain = true)
public class TextCompletionRsp {
    // 正常返回
    /**
     * 请求身份
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
}
