package com.digital.gpt.chat.openai;

import org.springframework.util.DigestUtils;

import java.nio.charset.StandardCharsets;

/**
 * OpenAi辅助方法
 *
 * @author lihuagang
 * @date 2023/5/15
 */
public final class OpenAiUtil {
    /**
     * 对消息内容进行md5加密
     *
     * @param message 消息内容
     * @param secret 加签密钥
     * @return 十六进制加密后的消息内容
     */
    public static String applySign(String message, String secret) {
        String data = message + secret;
        byte[] dataBytes = data.getBytes(StandardCharsets.UTF_8);
        return DigestUtils.md5DigestAsHex(dataBytes);
    }
}
