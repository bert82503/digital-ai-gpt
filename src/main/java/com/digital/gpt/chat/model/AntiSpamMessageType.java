package com.digital.gpt.chat.model;

/**
 * 反垃圾邮件的消息类型
 *
 * @author lihuagang
 * @date 2023/5/17
 */
public final class AntiSpamMessageType {
    /**
     * 正常消息
     */
    public static final int NORMAL_MESSAGE = 0;
    /**
     * 垃圾消息，包含敏感词
     */
    public static final int SPAM_MESSAGE = 1;
}
