package com.nicehancy.mix.service.aop;

import com.google.common.base.Throwables;
import com.nicehancy.mix.common.Result;
import com.nicehancy.mix.common.constant.CommonConstant;
import com.nicehancy.mix.common.exception.ExceptionUtil;
import com.nicehancy.mix.common.exception.MixErrorCode;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.StopWatch;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;

/**
 * aop日志处理
 * <p>
 * <p/>
 *
 * @author hancaiyun
 * @since 2020/7/20 18:15
 **/
@Slf4j
//@Component
//@Aspect
public class LogAdvise {

    /**
     * 所有service实例
     */
    @Pointcut("execution(* com.nicehancy.mix.service.*Impl.*(..))")
    void servicePointcut() {
    }

    /**
     * service日志处理，打印方法名，方法处理时间，参数,统一封装异常
     * @param joinPoint 切点
     */
    @Around(value = "com.nicehancy.mix.service.aop.LogAdvise.servicePointcut()")
    public Object resultHandler(ProceedingJoinPoint joinPoint) {
        StopWatch clock = new StopWatch();
        String clazzName = joinPoint.getTarget().getClass().getSimpleName();
        String methodName = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        Result result;
        try {
            clock.start();
            initMDC(args);
            log.info("call [{}][{}] PARAMETER:{}", clazzName, methodName, getLog(args));
            result = (Result) joinPoint.proceed();
            clock.stop();
        } catch (Throwable e) {
            log.debug("", e);
            result = ExceptionUtil.doException(e);
            if (MixErrorCode.SYSTEM_INNER_ERROR.getErrorCode().equals(result.getErrorCode())) {
                log.error("call [{}][{}] EXCEPTION:Cause[{}]", clazzName, methodName,
                        Throwables.getStackTraceAsString(e));
            }
        }
        if (result.isSuccess()) {
            log.info("call [{}][{}][{}ms][Success][0000] RESPONSE:{}",
                    clazzName, methodName, clock.getTime(), result);
        } else {
            log.warn("call [{}][{}][{}ms][Failure][{}],RESPONSE:{}",
                    clazzName, methodName, clock.getTime(), result.getErrorCode(), result);
        }
        return result;
    }

    /**
     * 获取日志信息
     *
     * @param args  args
     * @return
     */
    private String getLog(Object[] args) {
        StringBuffer logSb = new StringBuffer("[");
        for (int i = 0; i < args.length; i++) {
            Object arg = args[i];
            logSb.append(arg == null ? null : arg.toString());
            if (i != args.length - 1) {
                logSb.append(",");
            }
        }
        logSb.append("]");
        return logSb.toString();
    }

    /**
     * 初始化MDC
     *
     * @param args  args
     */
    private void initMDC(Object[] args) {
        Object transLogId = args[args.length - 1];
        MDC.put(CommonConstant.TRACE_LOG_ID, null != transLogId ? (String) transLogId : null);
    }
}
