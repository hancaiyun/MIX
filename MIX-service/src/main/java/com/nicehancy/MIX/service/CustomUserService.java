package com.nicehancy.MIX.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *     将用户权限交给springsecurity进行管控
 * <p/>
 *
 * @author hancaiyun
 * @since 2019/11/4 15:22
 **/
@Slf4j
@Service
public class CustomUserService implements UserDetailsService {

    /**
     * 登陆验证时，通过username获取用户的所有权限信息
     * 并返回UserDetails放到spring的全局缓存SecurityContextHolder中，以供授权器使用
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        log.info("call CustomUserService loadUserByUsername, parameter:{}", username);
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        //用于添加用户的权限。只要把用户权限添加到authorities
        authorities.add(new SimpleGrantedAuthority("ADMIN"));

        MUserDetails userDetails = new MUserDetails();
        userDetails.setUsername(username);
        userDetails.setPassword("$2a$10$xDRS1CbwUGaT1QqasT5UU.G3Z7jKtcphSiE/pP0rqbDX87D7KYe4S");//1234

        userDetails.setAuthorities(authorities);
        return userDetails;
    }
}
