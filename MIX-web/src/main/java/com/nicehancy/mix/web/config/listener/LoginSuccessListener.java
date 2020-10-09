package com.nicehancy.mix.web.config.listener;

import com.nicehancy.mix.common.utils.ThreadPoolUtil;
import com.nicehancy.mix.service.LoginAndOutOperationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

/**
 * 登陆授权成功事件监听器
 * <p>
 * <p/>
 *
 * @author hancaiyun
 * @since 2020/8/11 13:45
 **/
@Component
public class LoginSuccessListener implements ApplicationListener {

    @Autowired
    private LoginAndOutOperationServiceImpl loginAndOutOperationService;

    @Override
    public void onApplicationEvent(ApplicationEvent applicationEvent) {
        if (applicationEvent instanceof AuthenticationSuccessEvent) {
            AuthenticationSuccessEvent authEvent = (AuthenticationSuccessEvent) applicationEvent;
            UserDetails user = (UserDetails) authEvent.getAuthentication().getPrincipal();
            //登录成功后处理
            ThreadPoolUtil.execute(()-> loginAndOutOperationService.loginOperation(user.getUsername()));
        }
    }
}
