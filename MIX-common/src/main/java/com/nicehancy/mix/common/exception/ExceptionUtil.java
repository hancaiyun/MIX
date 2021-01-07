package com.nicehancy.mix.common.exception;

import com.nicehancy.mix.common.Result;
import lombok.extern.slf4j.Slf4j;

/**
 * <p>
 *     异常统一处理类
 * <p/>
 *
 * @author hancaiyun
 * @since 2021/1/5 12:38
 **/
@Slf4j
public class ExceptionUtil {

    private ExceptionUtil() {}

    /**
     * 统一异常处理
     *
     * @param err 异常
     * @return 外部响应对象
     */
    public static <T> Result<T> doException(Throwable err) {
        try {
            if (err instanceof MixException) {
                MixException e = (MixException) err;
                return new Result<>(e.getErrorCode(),e.getMessage());
            }
            if (err instanceof IllegalArgumentException) {
                return new Result<>(MixErrorCode.PARAMETER_VALID_NOT_PASS.getErrorCode(),
                                    MixErrorCode.PARAMETER_VALID_NOT_PASS.getErrorMsg());
            }
        } catch (Exception e) {
            log.error("call ExceptionUtil doException error：", e);
        }
        return new Result<>(MixErrorCode.SYSTEM_INNER_ERROR.getErrorCode(),
                            MixErrorCode.SYSTEM_INNER_ERROR.getErrorMsg());

    }

//    public static <T> Result<T> doLoginException(Throwable err) {
//        try {
//            if (err instanceof MixException) {
//                MixException e = (MixException) err;
//                if(LoginErrorList.getLoginError(e.getErrorCode())!=null){
//                    return new Result<>(CommonErrorCode.ACCOUNT_OR_PASSWORD_ERROR.getCode(),
//                            CommonErrorCode.ACCOUNT_OR_PASSWORD_ERROR.getDesc());
//                }
//                return new Result<>(e.getErrorCode(),e.getMessage());
//            }
//            if (err instanceof IllegalArgumentException) {
//                return new Result<>(CommonErrorCode.PARAMETER_VALID_NOT_PASS.getCode(),
//                        CommonErrorCode.PARAMETER_VALID_NOT_PASS.getDesc());
//            }
//        } catch (Exception e) {
//            log.error("call ExceptionUtil doExceptionService：{}", e);
//        }
//        return new Result<>(CommonErrorCode.SYSTEM_INNER_ERROR.getCode(),
//                CommonErrorCode.SYSTEM_INNER_ERROR.getDesc());
//
//    }
}
