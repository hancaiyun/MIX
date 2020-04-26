package com.nicehancy.MIX.manager.user;

import com.nicehancy.MIX.common.enums.SensitiveTypeEnum;
import com.nicehancy.MIX.common.utils.*;
import com.nicehancy.MIX.dal.UserInfoRepository;
import com.nicehancy.MIX.manager.convert.UserInfoBOConvert;
import com.nicehancy.MIX.manager.model.UserInfoBO;
import com.nicehancy.MIX.manager.redis.RedisManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * 用户管理manager
 * <p>
 * <p/>
 *
 * @author hancaiyun
 * @since 2020/1/2 20:01
 **/
@Component
public class UserInfoManager {

    @Autowired
    private UserInfoRepository userInfoRepository;

    @Autowired
    private RedisManager redisManager;

    /**
     * 根据用户名查询用户信息
     * @param userNo          账号
     * @return                用户信息
     */
    public UserInfoBO queryByUserName(String userNo) {

        return UserInfoBOConvert.getBOByDO(userInfoRepository.queryByUserNo(userNo));
    }

    /**
     * 发送短信验证码
     * @param phone             手机号
     * @return                  发送结果
     */
    public boolean sendVercode(String phone) {

        try {
            //发送短信
            String vercode = SendSmsUtil.sendVercode(phone);
            //将验证码放入缓存中， 并设置超时时间为5分钟
            redisManager.insertObject(vercode, phone, 300);
        } catch (Exception e) {
            throw new RuntimeException("发送验证码失败，请稍后重试！");
        }
        return true;
    }

    /**
     * 注册用户
     * @param userInfoBO      用户信息
     * @return                结果
     */
    public boolean addUser(UserInfoBO userInfoBO) {

        userInfoRepository.addUser(UserInfoBOConvert.getDOByBO(userInfoBO));
        return true;
    }

    /**
     * 更新用户密码
     * @param userNo           用户名
     * @param password         密码明文
     * @return                 邮箱
     */
    public String resetPassword(String userNo, String password) {

        //查询用户信息
        UserInfoBO user = queryByUserName(userNo);
        if(user == null){
            throw new RuntimeException("用户不存在！");
        }
        if(StringUtils.isEmpty(user.getEmail()) || !RegularValidatorUtil.isEmail(user.getEmail())){
            throw new RuntimeException("用户邮箱信息有误！");
        }

        //重置密码
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String encodePassword = bCryptPasswordEncoder.encode(password);
        userInfoRepository.resetPassword(userNo, encodePassword);

        //异步发送邮件通知
        //设置邮件主题与内容
        String subject = "MIX登陆密码重置提醒";
        String content = "您的账户密码已重置, 重置后的密码为：" + password;
        SendEmailUtil.sendEmail(subject, content, user.getEmail());

        //返回邮箱，掩码处理
        return MaskUtil.getMask(SensitiveTypeEnum.EMAIL.getCode(), user.getEmail());
    }

    /**
     * 修改密码
     * @param userNo            用户名
     * @param oldPassword       原密码
     * @param newPassword       新密码
     * @return                  修改结果
     */
    public boolean modifyPassword(String userNo, String oldPassword, String newPassword) {

        //查询用户信息
        UserInfoBO user = queryByUserName(userNo);
        if(user == null){
            throw new RuntimeException("用户不存在！");
        }

        //密码无效性修改校验
        if(oldPassword.equals(newPassword)){
            throw new RuntimeException("新密码与原密码一样，无需修改！");
        }

        //原密码比对
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        if(!bCryptPasswordEncoder.matches(oldPassword, user.getPassword())){
            throw new RuntimeException("原密码错误！");
        }

        //更新密码
        userInfoRepository.resetPassword(userNo, bCryptPasswordEncoder.encode(newPassword));

        return true;
    }

    /**
     * 更新用户信息
     * @param userInfoBO          用户信息
     * @return                    更新结果
     */
    public boolean updateUserInfo(UserInfoBO userInfoBO) {

        userInfoRepository.updateUserInfo(UserInfoBOConvert.getDOByBO(userInfoBO));
        return true;
    }
}
