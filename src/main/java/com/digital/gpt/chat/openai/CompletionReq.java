package com.digital.gpt.chat.openai;

import com.digital.gpt.chat.common.enumerate.ModelTypeEnum;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 补全指令入参
 *
 * @author lihuagang
 * @date 2023/5/14
 */
@Data
@Accessors(chain = true)
public class CompletionReq {
    /**
     * 组织身份
     */
    private Long orgId;
    /**
     * 用户身份
     */
    private Long userId;

    /**
     * 请求身份
     */
    private String requestId;
    /**
     * 业务身份，可选
     */
    private String bizId;

    /**
     * 模型类型
     */
    private ModelTypeEnum modelType;
    /**
     * 提示
     */
    private String prompt;
    /**
     * 消息内容
     */
    private String message;
    /**
     * 限制单次最大回复单词数(tokens)，11-3500
     */
    private int maxTokens;
}
