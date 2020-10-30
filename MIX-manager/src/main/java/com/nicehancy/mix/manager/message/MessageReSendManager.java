package com.nicehancy.mix.manager.message;

import com.nicehancy.mix.common.enums.BusinessTypeEnum;
import com.nicehancy.mix.common.utils.RegularValidatorUtil;
import com.nicehancy.mix.manager.model.MessageSendReqBO;
import com.nicehancy.mix.manager.user.UserInfoManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 消息发送manager
 * <p>
 * <p/>
 *
 * @author hancaiyun
 * @since 2020/6/11 17:36
 **/
@Component
public class MessageReSendManager {

    @Autowired
    private UserInfoManager userInfoManager;

    /**
     * 消息补发
     * @param messageSendReqBO      BO
     */
    public void sendMessage(MessageSendReqBO messageSendReqBO) {
        //根据业务类型进行补发
        //密码重置
        if(BusinessTypeEnum.PASSWORD_RESET.getCode().equals(messageSendReqBO.getBusinessType())){
            //邮箱验证
            if(RegularValidatorUtil.isEmail(messageSendReqBO.getRecipient())){
                throw new RuntimeException("暂仅支持邮箱！");
            }
            userInfoManager.resetPassword(messageSendReqBO.getRecipient());
        }
        //用户注册(仅用于注册时收不到验证码需要手动补发的场景)
        if(BusinessTypeEnum.USER_REGISTER.getCode().equals(messageSendReqBO.getBusinessType())){
            //手机号验证
            if(!RegularValidatorUtil.isPhone(messageSendReqBO.getRecipient())){
                throw new RuntimeException("暂仅支持手机号！");
            }
            userInfoManager.sendVercode(messageSendReqBO.getRecipient());
        }
    }
}
