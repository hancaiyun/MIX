package com.nicehancy.mix.common.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * <p>
 *     异常类
 * <p/>
 *
 * @author hancaiyun
 * @since 2021/1/5 12:34
 **/
@Getter
@AllArgsConstructor
public enum MixErrorCode {

    /**
     * 系统内部错误
     */
    SYSTEM_INNER_ERROR("SYSTEM_INNER_ERROR", "系统内部错误"),

    /**
     * 参数校验不通过
     */
    PARAMETER_VALID_NOT_PASS("PARAMETER_VALID_NOT_PASS", "参数校验不通过"),

    ;

    private final String errorCode;

    private final String errorMsg;
}
