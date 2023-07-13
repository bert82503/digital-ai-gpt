package com.digital.gpt.chat.openai;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 补全指令出参
 *
 * @author lihuagang
 * @date 2023/5/14
 */
@Data
@Accessors(chain = true)
public class CompletionRsp {
    // 正常返回
    /**
     * 第三方身份，消息身份
     */
    private String thirdPartyId;
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
