package com.digital.gpt.chat.common.config;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 聊天对话提示语配置项
 *
 * @author lihuagang
 * @date 2023/5/22
 */
@Slf4j
@Data
@ConfigurationProperties(prefix = "ai.gpt.openai.chat.prompt")
public class OpenAiChatPromptProperties {
    /**
     * 润色改写
     */
    private String rewritePolish;

    /**
     * 生成摘要
     */
    private String generateSummary;

    /**
     * 重新生成摘要
     */
    private String regenerateSummary;

    /**
     * 翻译成不同的语言
     */
    private String translate;

    public OpenAiChatPromptProperties() {
        log.info("create OpenAiChatPromptProperties instance");
    }
}
