package com.digital.gpt.chat.common.tools.exception;

/**
 * 参数错误异常
 *
 * @author lihuagang
 * @date 2023/7/13
 */
public class ParamErrorException extends ApiException {
    private static final long serialVersionUID = -2021166984141782620L;

    public ParamErrorException() {
        super("参数错误", 400);
    }

    public ParamErrorException(final String message) {
        super(message, 400);
    }

    public ParamErrorException(String message, int code) {
        super(message, code);
    }
}
