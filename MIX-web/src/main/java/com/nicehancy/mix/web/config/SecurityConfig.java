package com.nicehancy.mix.web.config;

import com.nicehancy.mix.service.user.CustomUserServiceImpl;
import com.nicehancy.mix.web.config.handler.MyLogoutSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.web.filter.CharacterEncodingFilter;

/**
 * 重写SpringSecurity
 * <p>
 * <p/>
 *
 * @author hancaiyun
 * @since 2019/11/4 15:15
 **/
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * 注册UserDetailsService的bean
     */
    @Bean
    UserDetailsService CustomUserService(){
        return new CustomUserServiceImpl();
    }

    @Autowired
    private MyLogoutSuccessHandler myLogoutSuccessHandler;

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        //解决iframe框架不允许内嵌问题
        http.headers().frameOptions().disable();

        http.cors().and().csrf().disable();
        http
             //使用form表单post方式进行登录
             .formLogin()
             //登录页面为自定义的登录页面
             .loginPage("/login")
             //设置登录成功跳转页面，error=true控制页面错误信息的展示
             .successForwardUrl("/index").failureUrl("/login?error=true")
             .permitAll()
             .and()
             //允许不登陆就可以访问的方法，多个用逗号分隔
             .authorizeRequests().antMatchers("/reg/**","/user/password/reset","/user/resetPassword",
                "/frame/**","/healthcheck").permitAll()
             //其他的需要授权后访问
             .anyRequest().authenticated();

        //session管理,失效后跳转
        http.sessionManagement().invalidSessionUrl("/login");
        //单用户登录，如果有一个登录了，同一个用户在其他地方登录将前一个剔除下线
        //http.sessionManagement().maximumSessions(1).expiredSessionStrategy(expiredSessionStrategy());
        //单用户登录，如果有一个登录了，同一个用户在其他地方不能登录
        http.sessionManagement().maximumSessions(1).maxSessionsPreventsLogin(true);
        //自定义退出页，退出时清空cookies并跳转到登录页
        http.logout().logoutSuccessHandler(myLogoutSuccessHandler).logoutSuccessUrl("/login").deleteCookies("JSESSIONID");
        //解决中文乱码问题
        CharacterEncodingFilter filter = new CharacterEncodingFilter();
        filter.setEncoding("UTF-8");
        filter.setForceEncoding(true);
        //设置过期
        http.addFilterBefore(filter, CsrfFilter.class);
    }

    /**
     * 认证密码加密, BCrypt方式
     * @param  auth             权限
     * @throws Exception        异常
     */
    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception{
        auth.userDetailsService(CustomUserService()).passwordEncoder(new BCryptPasswordEncoder());
    }
}
