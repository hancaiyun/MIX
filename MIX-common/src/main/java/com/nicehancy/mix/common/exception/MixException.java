package com.nicehancy.mix.common.exception;

import lombok.Getter;

import java.io.Serializable;

/**
 * <p>
 *     异常类
 * <p/>
 *
 * @author hancaiyun
 * @since 2021/1/5 13:40
 **/
public class MixException extends RuntimeException implements Serializable {

    private static final long serialVersionUID = 8125948578744627057L;

    @Getter
    private final String errorCode;

    public MixException(String errorCode) {
        this.errorCode = errorCode;
    }

    public MixException(String errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

    public MixException(MixErrorCode mixErrorCode) {
        super(mixErrorCode.getErrorMsg());
        this.errorCode = mixErrorCode.getErrorCode();
    }
}
