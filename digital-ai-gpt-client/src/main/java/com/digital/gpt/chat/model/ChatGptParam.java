package com.digital.gpt.chat.model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.io.Serializable;

/**
 * 对话入参
 *
 * @author lihuagang
 * @date 2023/5/19
 */
@Data
public class ChatGptParam implements Serializable {
    /**
     * 组织身份
     */
    @Positive(message = "组织身份需要超过0")
    private Long orgId;
    /**
     * 用户身份
     */
    @Positive(message = "用户身份需要超过0")
    private Long userId;

    /**
     * 请求身份，可选
     */
    private String requestId;
    /**
     * 业务身份，可选
     */
    private String bizId;

    /**
     * 消息内容
     */
    @NotEmpty(message = "消息内容不能为空")
    @Size(max = 2000, message = "消息内容长度不能超过2000个字")
    private String message;
    /**
     * 限制单次最大回复单词数(tokens)，11-3500
     */
    private int maxTokens;

    // 翻译
    /**
     * 翻译的源头语言
     */
    private String sourceLang;
    /**
     * 翻译的目标语言/译文语言
     */
    private String targetLang;

    // 推理/抽取主题
    /**
     * 标题风格
     */
    private String titleStyle;

    private static final long serialVersionUID = 1L;
}
