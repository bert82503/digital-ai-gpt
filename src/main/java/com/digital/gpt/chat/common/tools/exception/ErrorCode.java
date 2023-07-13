package com.digital.gpt.chat.common.tools.exception;

import cn.hutool.json.JSONUtil;
import lombok.Data;

/**
 * 复制公共库代码，避免报错，忽略。
 *
 * @author lihuagang
 * @date 2023/7/13
 */
@Data
public class ErrorCode {

    private int code;
    private String msg;

    public ErrorCode(final int code, final String msg) {
        this.code = code;
        this.msg = msg;
    }

    @Override
    public String toString() {
        return JSONUtil.toJsonStr(this);
    }
}
