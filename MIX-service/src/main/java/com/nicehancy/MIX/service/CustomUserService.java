package com.nicehancy.MIX.service;

import com.alibaba.fastjson.JSON;
import com.nicehancy.MIX.common.Result;
import com.nicehancy.MIX.common.utils.GsonUtil;
import com.nicehancy.MIX.common.utils.PasswordUtil;
import com.nicehancy.MIX.manager.model.UserInfoBO;
import com.nicehancy.MIX.manager.redis.RedisManager;
import com.nicehancy.MIX.manager.user.UserInfoManager;
import com.nicehancy.MIX.service.api.model.UserInfoDTO;
import com.nicehancy.MIX.service.convert.user.UserInfoDTOConvert;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

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

    @Autowired
    private UserInfoManager userInfoManager;

    @Autowired
    private RedisManager redisManager;

    /**
     * 登陆验证时，通过username获取用户的所有权限信息
     * 并返回UserDetails放到spring的全局缓存SecurityContextHolder中，以供授权器使用
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        log.info("call CustomUserService loadUserByUsername, parameter:{}", username);
        UserInfoBO user = userInfoManager.queryByUserName(username);
        log.info("call CustomUserService loadUserByUsername result:{}", user);
        if(user == null){
            throw new RuntimeException("用户名不存在！");
        }
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        //用于添加用户的权限。只要把用户权限添加到authorities
        authorities.add(new SimpleGrantedAuthority(user.getAuthCode()));

        MUserDetails userDetails = new MUserDetails();
        userDetails.setUsername(user.getNickName());
        userDetails.setPassword(user.getPassword());
        userDetails.setAuthorities(authorities);
        return userDetails;
    }

    /**
     * 短信验证码发送接口
     * @param phone                     手机号
     * @param traceLogId                日志ID
     * @return
     */
    public Result<Boolean> sendVercode(String phone, String traceLogId){

        Result<Boolean> result = new Result<>();
        MDC.put("TRACE_LOG_ID", traceLogId);
        try {
            log.info("CustomUserService sendVercode request PARAM: phone={}, traceLogId={}", phone, traceLogId);
            //发送短信验证码
            boolean isOk = userInfoManager.sendVercode(phone);
            result.setResult(isOk);
            log.info("CustomUserService sendVercode result: result={}", result);
        }catch (Exception e){
            result.setErrorCode("SYSTEM_ERROR");
            result.setErrorMsg(e.getMessage());
            log.error("CustomUserService sendVercode error: result={}, e={}", result, e);
        }
        return result;
    }

    /**
     * 新增用户请求
     * @param userInfoDTO           用户信息DTO
     * @param traceLogId            日志ID
     * @return                      注册结果
     */
    public Result<Boolean> register(UserInfoDTO userInfoDTO, String traceLogId) {

        Result<Boolean> result = new Result<>();
        MDC.put("TRACE_LOG_ID", traceLogId);
        try {
            log.info("CustomUserService register request PARAM: userInfoDTO={}, traceLogId={}", userInfoDTO, traceLogId);
            //查询用户是否已存在
            UserInfoBO user = userInfoManager.queryByUserName(userInfoDTO.getLoginNo());
            if(user != null){
                throw new RuntimeException("用户已存在！");
            }
            //验证码校验
            String cache = GsonUtil.fromJson(redisManager.queryObjectByKey(userInfoDTO.getLoginNo()), String.class);
            if(StringUtils.isEmpty(cache)){
                throw new RuntimeException("验证码已失效！");
            }
            if(!cache.equals(userInfoDTO.getVercode())){
                log.error("缓存中的验证码：{}, 用户输入的验证码：{}", cache, userInfoDTO.getVercode());
                throw new RuntimeException("验证码有误！");
            }

            //密码加密，通过BCrypt
            String password = userInfoDTO.getPassword();
            BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
            userInfoDTO.setPassword(bCryptPasswordEncoder.encode(password));
            boolean isOk = userInfoManager.addUser(UserInfoDTOConvert.getBOByDTO(userInfoDTO));
            result.setResult(isOk);
            log.info("CustomUserService register result: result={}", result);
        }catch (Exception e){
            result.setErrorCode("SYSTEM_ERROR");
            result.setErrorMsg(e.getMessage());
            log.error("CustomUserService register error: result={}, e={}", result, e);
        }
        return result;
    }

    /**
     * 密码重置接口
     * @param userNo                用户名
     * @param traceLogId            日志ID
     * @return                      邮箱
     */
    public Result<String> resetPassword(String userNo, String traceLogId) {

        Result<String> result = new Result<>();
        MDC.put("TRACE_LOG_ID", traceLogId);
        try {
            log.info("CustomUserService resetPassword request PARAM: userNo={}, traceLogId={}", userNo, traceLogId);

            //密码加密，通过BCrypt
            String password = PasswordUtil.randomPassword();
            String email = userInfoManager.resetPassword(userNo, password);
            result.setResult(email);
            log.info("CustomUserService resetPassword result: result={}", result);
        }catch (Exception e){
            result.setErrorCode("SYSTEM_ERROR");
            result.setErrorMsg(e.getMessage());
            log.error("CustomUserService resetPassword error: result={}, e={}", result, e);
        }
        return result;
    }

    /**
     * 密码修改接口
     * @param userNo                用户名
     * @param oldPassword           密码
     * @param newPassword           新密码
     * @param traceLogId            日志ID
     * @return                      返回修改结果
     */
    public Result<Boolean> modifyPassword(String userNo, String oldPassword, String newPassword, String traceLogId) {

        Result<Boolean> result = new Result<>();
        MDC.put("TRACE_LOG_ID", traceLogId);
        try{
            log.info("CustomUserService modifyPassword request PARAM: userNo={}, oldPassword={}, newPassword={}, " +
                    "traceLogId={}", userNo, oldPassword, newPassword, traceLogId);
            //TODO 参数校验

            //密码修改
            boolean isModify = userInfoManager.modifyPassword(userNo, oldPassword, newPassword);
            result.setResult(isModify);
            log.error("CustomUserService modifyPassword success: result={}", result);
        }catch (Exception e){
            result.setErrorCode("SYSTEM_ERROR");
            result.setErrorMsg(e.getMessage());
            log.error("CustomUserService modifyPassword error: result={}, e={}", result, e);
        }
        return result;
    }
}
