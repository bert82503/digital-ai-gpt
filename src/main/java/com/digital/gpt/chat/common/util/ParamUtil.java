package com.digital.gpt.chat.common.util;

import lombok.extern.slf4j.Slf4j;

/**
 * 参数辅助方法
 * <pre>
 * 使用场景：
 * 参数校验
 * </pre>
 *
 * @author lihuagang
 * @date 2023/5/15
 */
@Slf4j
public final class ParamUtil {

    public static void check(Long orgId, Long userId) {
        if (orgId == null || orgId <= 0L) {
            log.error("orgId must be greater than 0, orgId={}", orgId);
            throw new IllegalArgumentException(
                    String.format("orgId must be greater than 0, orgId=%s", orgId)
            );
        }

        if (userId == null || userId <= 0L) {
            log.error("userId must be greater than 0, userId={}", userId);
            throw new IllegalArgumentException(
                    String.format("userId must be greater than 0, userId=%s", userId)
            );
        }
    }
}
