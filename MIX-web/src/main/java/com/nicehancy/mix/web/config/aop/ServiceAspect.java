package com.nicehancy.mix.web.config.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.slf4j .Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

/**
 * <p>
 *     业务层切面
 * <p/>
 *
 * @author hancaiyun
 * @since 2020/11/19 14:45
 **/
@Aspect
@Component
public class ServiceAspect {

    private final Logger log = LoggerFactory.getLogger(ServiceAspect.class );

    @Around("execution(* com.nicehancy.mix.service..*.*(..))")
    public Object aroundInvoke(ProceedingJoinPoint point) throws Throwable {

        this.log.info("【*** aop Service-Before ***】执行参数："+ Arrays.toString (point.getArgs()));
        Object obj = point.proceed(point.getArgs());
        this.log.info("【*** aop Service After ***】返回结果 ："+ obj);

        return obj;
    }
}
