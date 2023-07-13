package com.digital.gpt.chat.common.util;

import java.util.UUID;

/**
 * uuid辅助方法
 *
 * @author lihuagang
 * @date 2023/5/16
 */
public final class UuidUtil {
    /**
     * 提供通用唯一识别码（universally unique identifier）（UUID）实现
     */
    public static String getUuid() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }
}
