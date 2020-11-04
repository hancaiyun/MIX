package com.nicehancy.mix.manager.user;

import com.nicehancy.mix.common.constant.CommonConstant;
import com.nicehancy.mix.common.enums.MessageTypeEnum;
import com.nicehancy.mix.common.enums.SendResultEnum;
import com.nicehancy.mix.common.enums.SensitiveTypeEnum;
import com.nicehancy.mix.common.utils.*;
import com.nicehancy.mix.dal.MessageSendRecordRepository;
import com.nicehancy.mix.dal.UserInfoRepository;
import com.nicehancy.mix.dal.model.MessageSendRecordDO;
import com.nicehancy.mix.manager.convert.UserInfoBOConvert;
import com.nicehancy.mix.manager.model.UserInfoBO;
import com.nicehancy.mix.manager.redis.RedisManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Random;

/**
 * 用户管理manager
 * <p>
 * <p/>
 *
 * @author hancaiyun
 * @since 2020/1/2 20:01
 **/
@Slf4j
@Component
public class UserInfoManager {

    @Autowired
    private UserInfoRepository userInfoRepository;

    @Autowired
    private MessageSendRecordRepository  messageSendRecordRepository;

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

        //初始化短信发送记录
        MessageSendRecordDO messageSendRecordDO = new MessageSendRecordDO();
        messageSendRecordDO.setMessageId(UUIDUtil.createNoByUUId());
        messageSendRecordDO.setMessageType(MessageTypeEnum.MESSAGE.getCode());
        messageSendRecordDO.setMessageName(MessageTypeEnum.MESSAGE.getDesc());
        messageSendRecordDO.setSender(CommonConstant.SYSTEM);
        messageSendRecordDO.setRecipient(phone);
        messageSendRecordDO.setCreatedBy(CommonConstant.SYSTEM);
        messageSendRecordDO.setUpdatedBy(CommonConstant.SYSTEM);

        try {

            //生成6位验证码
            String verifyCode = String.valueOf(new Random().nextInt(899999) + 100000);
            //设置短信内容
            String message = "欢迎注册MIX账号，您的验证码为:" + verifyCode + "，该码5分钟内有效，打死不要告诉别人哦！";
            //将验证码放入缓存中， 并设置超时时间为5分钟
            redisManager.insertObject(verifyCode, phone, 300);
            //发送短信
            SendSmsUtil.sendVercode(phone, message);
            //设置消息发送记录内容信息
            messageSendRecordDO.setContent(message);
            messageSendRecordDO.setSendResult(SendResultEnum.SUCCESS.getCode());
        } catch (Exception e) {
            messageSendRecordDO.setSendResult(SendResultEnum.FAILURE.getCode());
            messageSendRecordDO.setReason(e.getMessage());
            log.error("发送短信验证码失败，失败原因:", e);
            throw new RuntimeException("发送验证码失败，请稍后重试！");
        }
        //新增消息记录
        messageSendRecordRepository.insert(messageSendRecordDO);
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
     * @return                 邮箱
     */
    public String resetPassword(String userNo) {

        //查询用户信息
        UserInfoBO user = queryByUserName(userNo);
        if(user == null){
            throw new RuntimeException("用户不存在！");
        }
        if(StringUtils.isEmpty(user.getEmail()) || RegularValidatorUtil.isEmail(user.getEmail())){
            throw new RuntimeException("用户邮箱信息有误！");
        }

        //重置密码, 并加密
        String password = PasswordUtil.randomPassword();
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String encodePassword = bCryptPasswordEncoder.encode(password);
        userInfoRepository.resetPassword(userNo, encodePassword);

        //异步发送邮件通知
        //初始化短信发送记录
        MessageSendRecordDO messageSendRecordDO = new MessageSendRecordDO();
        messageSendRecordDO.setMessageId(UUIDUtil.createNoByUUId());
        messageSendRecordDO.setMessageType(MessageTypeEnum.EMAIL.getCode());
        messageSendRecordDO.setMessageName(MessageTypeEnum.EMAIL.getDesc());
        messageSendRecordDO.setSender(CommonConstant.SYSTEM);
        messageSendRecordDO.setRecipient(user.getEmail());
        messageSendRecordDO.setCreatedBy(CommonConstant.SYSTEM);
        messageSendRecordDO.setUpdatedBy(CommonConstant.SYSTEM);
        //设置邮件主题与内容
        String subject = "MIX登陆密码重置提醒";
        String content = "您的账户密码已重置, 重置后的密码为：" + password;
        SendEmailUtil.sendEmail(subject, content, user.getEmail());

        //设置消息记录主体信息
        messageSendRecordDO.setSendResult(SendResultEnum.SUCCESS.getCode());
        messageSendRecordDO.setContent(content);
        //新增消息发送记录
        messageSendRecordRepository.insert(messageSendRecordDO);

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
