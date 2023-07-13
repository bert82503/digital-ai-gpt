package com.digital.gpt.chat.common.config;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.time.Duration;

/**
 * OpenAI生成属性配置项
 *
 * @author lihuagang
 * @date 2023/5/14
 */
@Slf4j
@Data
@ConfigurationProperties(prefix = "ai.gpt.openai")
public class OpenAiProperties {

    public OpenAiProperties() {
        log.info("create OpenAiProperties instance");
    }

    /**
     * 应用身份
     */
    private String appId;
    /**
     * 加签密钥
     */
    private String secret;
    /**
     * 限制单次最大回复结果数
     * 上下文关联长度，1-10
     */
    private int context;
    /**
     * 限制单次最大回复单词数，11-3500，不限制传空
     */
    private int maxTokens;
    // 第一条原则是写出清晰而具体的指示
    /**
     * 分隔符，定界符
     * <pre>
     * 使用_分隔符_来明确指出输入的不同部分
     * </pre>
     */
    private String delimiters;
    // 超时机制
    /**
     * 连接获取超时时间
     */
    private Duration connectionTimeout;
    /**
     * 请求超时时间
     */
    private Duration requestTimeout;
}
