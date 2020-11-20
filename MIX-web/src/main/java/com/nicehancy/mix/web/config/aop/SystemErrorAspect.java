package com.nicehancy.mix.web.config.aop;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * <p>
 *     系统错误切面
 * <p/>
 *
 * @author hancaiyun
 * @since 2020/11/19 14:45
 **/
//@Aspect
//@Component
public class SystemErrorAspect {

    /**
     * 设置切点
     */
    //@Pointcut("execution(public * com.nicehancy.mix.common.utils(..))")
    public void error(){
        System.out.println("aspect");
    }
    /**
     * 切点之前
     */
    //@Before("login()")
    public void beforelogin()
    {
        System.out.println("before");
    }

    /**
     * 切点之后
     */
    //@After("login()")
    public void afterlogin(){System.out.println("after");}
}
