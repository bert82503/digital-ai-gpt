package com.digital.gpt.chat.openai.impl;

import com.digital.gpt.chat.common.config.OpenAiProperties;
import com.digital.gpt.chat.openai.OpenAiTextService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

/**
 * OpenAI文本服务实现
 *
 * @author lihuagang
 * @date 2023/5/14
 */
@Slf4j
@Configuration(proxyBeanMethods = false)
@EnableConfigurationProperties(OpenAiProperties.class)
@Service("openAiTextService")
public class OpenAiTextServiceImpl implements OpenAiTextService {

    private final OpenAiProperties openAiProperties;

    public OpenAiTextServiceImpl(
            OpenAiProperties openAiProperties
    ) {
        this.openAiProperties = openAiProperties;
        log.info("create OpenAiTextServiceImpl instance");
    }

    @Override
    public void completions() {
        //
    }
}
