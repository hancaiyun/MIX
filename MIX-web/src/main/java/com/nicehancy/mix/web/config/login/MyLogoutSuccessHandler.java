package com.nicehancy.mix.web.config.login;

import com.nicehancy.mix.service.LoginAndOutOperationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 登出成功操作类
 * <p>
 * <p/>
 *
 * @author hancaiyun
 * @since 2020/8/11 14:08
 **/
@Component
public class MyLogoutSuccessHandler implements LogoutSuccessHandler {

    @Autowired
    private LoginAndOutOperationServiceImpl loginAndOutOperationService;

    @Override
    public void onLogoutSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                                Authentication authentication) throws IOException, ServletException {

        UserDetails user = (UserDetails) authentication.getPrincipal();
        loginAndOutOperationService.logoutOperation(user.getUsername());
        //重定向到登录页
        httpServletResponse.sendRedirect("/login");
    }
}
