package com.digital.gpt.chat.common.config;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 文本对话提示语配置项
 *
 * @author lihuagang
 * @date 2023/5/22
 */
@Slf4j
@Data
@ConfigurationProperties(prefix = "ai.gpt.openai.text.prompt")
public class OpenAiTextPromptProperties {
    /**
     * 续写补全
     */
    private String continuousWrite;

    /**
     * 润色改写
     */
    private String rewritePolish;

    /**
     * 扩写
     */
    private String expandWrite;

    /**
     * 帮写
     */
    private String helpWrite;

    /**
     * 提取主题
     */
    private String extractTopic;

    /**
     * 推理标题
     */
    private String inferTitle;

    /**
     * 列出大纲
     */
    private String listOutline;

    public OpenAiTextPromptProperties() {
        log.info("create OpenAiTextPromptProperties instance");
    }
}
