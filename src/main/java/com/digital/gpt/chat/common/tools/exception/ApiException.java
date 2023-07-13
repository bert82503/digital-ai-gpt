package com.digital.gpt.chat.common.tools.exception;

/**
 * 复制公共库代码，避免报错，忽略。
 *
 * @author lihuagang
 * @date 2023/7/13
 */
public class ApiException extends RuntimeException {
    private static final long serialVersionUID = 4666861106427943972L;

    private int code = 500;
    private String data = null;

    public ApiException() {
    }

    public ApiException(ErrorCode errorCode) {
        this(errorCode.getMsg(), errorCode.getCode());
    }

    public ApiException(String message, int code) {
        super(message, null, false, false);
        this.code = code;
    }

    public ApiException(String message, Throwable cause) {
        super(message, cause, false, false);
    }

    public ApiException(String message, int code, Throwable cause) {
        super(message, cause, false, false);
        this.code = code;
    }

    public ApiException(Throwable cause, String message, int code) {
        super(message, cause);
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public void setCode(final int code) {
        this.code = code;
    }

    public ApiException data(String data) {
        this.data = data;
        return this;
    }

    public String getData() {
        return this.data;
    }
}
