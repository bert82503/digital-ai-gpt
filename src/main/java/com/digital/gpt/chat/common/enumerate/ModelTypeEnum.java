package com.digital.gpt.chat.common.enumerate;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.baomidou.mybatisplus.annotation.IEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 模型类型
 *
 * @author lihuagang
 * @date 2023/5/16
 */
@Getter
@AllArgsConstructor
public enum ModelTypeEnum implements IEnum<Integer> {
    /**
     * 文本模型
     * <p></p>
     * 一条文本提示语(a prompt)
     */
    TEXT(1, "文本模型"),
    /**
     * 聊天对话模型
     * <p></p>
     * 聊天对话消息(a list of messages)
     */
    CHAT(2, "聊天对话模型"),
    ;

    /**
     * 枚举数据库存储值
     */
    @EnumValue
    private final Integer value;
    /**
     * 描述
     */
    private final String desc;
}
