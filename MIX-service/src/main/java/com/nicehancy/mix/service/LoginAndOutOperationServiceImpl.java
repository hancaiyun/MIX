package com.nicehancy.mix.service;

import com.nicehancy.mix.common.constant.CommonConstant;
import com.nicehancy.mix.manager.LoginAndOutOperationManager;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import java.util.UUID;

/**
 * 登录、登出业务逻辑service
 * <p>
 * <p/>
 *
 * @author hancaiyun
 * @since 2020/8/11 15:10
 **/
@Slf4j
@Service
public class LoginAndOutOperationServiceImpl {

    @Autowired
    private LoginAndOutOperationManager loginAndOutOperationManager;

    /**
     * 用户登录成功后处理
     * @param userNo           用户编号
     */
    public void loginOperation(String userNo){

        MDC.put(CommonConstant.TRACE_LOG_ID, UUID.randomUUID().toString());
        try{
            if(StringUtils.isEmpty(userNo)){
                log.error("the userNo is null or empty");
                return;
            }
            //处理逻辑
            loginAndOutOperationManager.loginOperation(userNo);
        }catch (Exception e){
            log.error("do LoginAndOutOperationServiceImpl loginOperation error, e", e);
        }
    }

    /**
     * 用户登出成功后处理
     * @param userNo           用户编号
     */
    public void logoutOperation(String userNo){

        MDC.put(CommonConstant.TRACE_LOG_ID, UUID.randomUUID().toString());
        try{
            if(StringUtils.isEmpty(userNo)){
                log.error("the userNo is null or empty");
                return;
            }
            //处理逻辑
            loginAndOutOperationManager.logoutOperation(userNo);
        }catch (Exception e){
            log.error("do LoginAndOutOperationServiceImpl logoutOperation error, e", e);
        }
    }
}
