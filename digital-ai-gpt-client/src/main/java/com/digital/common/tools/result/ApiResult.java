package com.digital.common.tools.result;

import lombok.Data;

/**
 * 复制公共库代码，避免报错，忽略。
 *
 * @author lihuagang
 * @date 2023/7/13
 */
@Data
public class ApiResult<T> {
    protected int code;
    protected String msg;
    protected T data;

    /**
     * 此方法不会copy data
     */
    public <D> ApiResult<D> cast() {
        return new ApiResult<>(msg, code);
    }

    /**
     * 此方法不会copy data
     */
    public <D> ApiResult<D> copy() {
        return new ApiResult<>(msg, code);
    }

    public static <T> ApiResult<T> success(T data) {
        return new ApiResult<>(data);
    }

    public static <T> ApiResult<T> fail(String message) {
        return new ApiResult<>(message, 500);
    }

    public static <T> ApiResult<T> fail(String message, int errorCode) {
        return new ApiResult<>(message, errorCode);
    }

    private ApiResult(final T data) {
        this.code = 0;
        this.data = data;
    }

    private ApiResult(final String msg, final int code) {
        this.code = code;
        this.msg = msg;
    }

    /**
     * 不要使用getCode() == 200 表示成功
     * 后续默认都是用0来表示成功
     * 请使用isSuccess()方法
     *
     * @return true == success
     */
    public boolean isSuccess() {
        //后续默认都是用0来表示成功
        return code == 0 || code == 200;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
