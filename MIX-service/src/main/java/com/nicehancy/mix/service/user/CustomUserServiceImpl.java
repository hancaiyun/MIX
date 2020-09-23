package com.nicehancy.mix.service.user;

import com.nicehancy.mix.common.Result;
import com.nicehancy.mix.common.utils.GsonUtil;
import com.nicehancy.mix.common.utils.ThreadPoolUtil;
import com.nicehancy.mix.common.utils.VerifyUtil;
import com.nicehancy.mix.manager.model.NoteSaveReqBO;
import com.nicehancy.mix.manager.model.UserInfoBO;
import com.nicehancy.mix.manager.note.NoteInfoManager;
import com.nicehancy.mix.manager.redis.RedisManager;
import com.nicehancy.mix.manager.user.UserInfoManager;
import com.nicehancy.mix.service.api.model.UserInfoDTO;
import com.nicehancy.mix.service.api.model.result.PasswordResetDTO;
import com.nicehancy.mix.service.convert.user.UserInfoDTOConvert;
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
import java.util.concurrent.CountDownLatch;

/**
 * <p>
 *     将用户权限交给springSecurity进行管控
 * <p/>
 *
 * @author hancaiyun
 * @since 2019/11/4 15:22
 **/
@Slf4j
@Service
public class CustomUserServiceImpl implements UserDetailsService {

    @Autowired
    private UserInfoManager userInfoManager;

    @Autowired
    private RedisManager redisManager;

    @Autowired
    private NoteInfoManager noteInfoManager;

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
        userDetails.setUsername(user.getUserNo());
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
     * 用户基本信息查询
     * @param userNo        用户名
     * @param traceLogId    日志ID
     * @return              用户信息
     */
    public Result<UserInfoDTO> queryUserInfo(String userNo, String traceLogId){

        Result<UserInfoDTO> result = new Result<>();
        MDC.put("TRACE_LOG_ID", traceLogId);
        try {
            log.info("CustomUserService queryUserInfo request PARAM: userNo={}, traceLogId={}", userNo, traceLogId);
            //查询用户是否已存在
            UserInfoBO user = userInfoManager.queryByUserName(userNo);
            if(user == null){
                throw new RuntimeException("用户信息不存在！");
            }

            result.setResult(UserInfoDTOConvert.getDTOByBO(user));
            log.info("CustomUserService queryUserInfo result: result={}", result);
        }catch (Exception e){
            result.setErrorCode("SYSTEM_ERROR");
            result.setErrorMsg(e.getMessage());
            log.error("CustomUserService queryUserInfo error: result={}, e={}", result, e);
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
            if(!cache.equals(userInfoDTO.getVerCode())){
                log.error("缓存中的验证码：{}, 用户输入的验证码：{}", cache, userInfoDTO.getVerCode());
                throw new RuntimeException("验证码有误！");
            }

            //密码加密，通过BCrypt
            String password = userInfoDTO.getPassword();
            BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
            userInfoDTO.setPassword(bCryptPasswordEncoder.encode(password));
            boolean isOk = userInfoManager.addUser(UserInfoDTOConvert.getBOByDTO(userInfoDTO));
            result.setResult(isOk);
            //新用户数据初始化
            ThreadPoolUtil.execute(()->initUser(userInfoDTO.getLoginNo()));
            log.info("CustomUserService register result: result={}", result);
        }catch (Exception e){
            result.setErrorCode("SYSTEM_ERROR");
            result.setErrorMsg(e.getMessage());
            log.error("CustomUserService register error: result={}, e={}", result, e);
        }
        return result;
    }

    /**
     * 初始化用户
     * @param userNo    用户编号
     */
    private void initUser(String userNo) {

        //初始化用户笔记信息
        initNote(userNo);
    }

    /**
     * 初始化用户笔记
     * @param userNo    用户编号
     */
    private void initNote(String userNo) {

       NoteSaveReqBO reqBO1 = new NoteSaveReqBO();
       reqBO1.setUserNo(userNo);
       reqBO1.setPrimaryDirectory("日记");
       reqBO1.setDocumentName("2020-07-21");
       reqBO1.setContent("今天是一个难忘的日子");
       noteInfoManager.saveNote(reqBO1);

       NoteSaveReqBO reqBO2 = new NoteSaveReqBO();
       reqBO2.setUserNo(userNo);
       reqBO2.setPrimaryDirectory("笔记");
       noteInfoManager.saveNote(reqBO2);

       NoteSaveReqBO reqBO3 = new NoteSaveReqBO();
       reqBO3.setUserNo(userNo);
       reqBO3.setPrimaryDirectory("备忘录");
       reqBO3.setDocumentName("每日");
       reqBO3.setContent("一定记得，按时吃饭");
       noteInfoManager.saveNote(reqBO3);
    }

    /**
     * 密码重置接口
     * @param userNo                用户名
     * @param traceLogId            日志ID
     * @return                      邮箱
     */
    public Result<PasswordResetDTO> resetPassword(String userNo, String traceLogId) {

        Result<PasswordResetDTO> result = new Result<>();
        MDC.put("TRACE_LOG_ID", traceLogId);
        try {
            log.info("CustomUserService resetPassword request PARAM: userNo={}, traceLogId={}", userNo, traceLogId);

            //密码重置
            String email = userInfoManager.resetPassword(userNo);
            PasswordResetDTO prDTO = new PasswordResetDTO();
            prDTO.setEmail(email);
            result.setResult(prDTO);
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

    /**
     * 用户信息修改接口
     * @param userInfoDTO           用户信息
     * @return                      返回修改结果
     */
    public Result<Boolean> updateUserInfo(UserInfoDTO userInfoDTO) {

        Result<Boolean> result = new Result<>();
        MDC.put("TRACE_LOG_ID", userInfoDTO.getTraceLogId());
        try{
            log.info("CustomUserService updateUserInfo request PARAM: userInfoDTO={}", userInfoDTO);
            //参数校验
            VerifyUtil.validateObject(userInfoDTO);

            //密码修改
            boolean isModify = userInfoManager.updateUserInfo(UserInfoDTOConvert.getBOByDTO(userInfoDTO));
            result.setResult(isModify);
            log.error("CustomUserService updateUserInfo success: result={}", result);
        }catch (Exception e){
            result.setErrorCode("SYSTEM_ERROR");
            result.setErrorMsg(e.getMessage());
            log.error("CustomUserService updateUserInfo error: result={}, e={}", result, e);
        }
        return result;
    }
}
