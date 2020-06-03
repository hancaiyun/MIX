package com.nicehancy.mix.service.api.model.request.message;

import com.nicehancy.mix.service.api.model.base.BaseReqDTO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 消息补发请求DTO
 * <p>
 * <p/>
 *
 * @author hancaiyun
 * @since 2020/5/9 10:47
 **/
@Getter
@Setter
@ToString
public class MessageSendReqDTO extends BaseReqDTO {

    private static final long serialVersionUID = -1808433468778452431L;

    /**
     * 消息类型
     *
     * 短信
     * 邮件
     */
    private String messageType;

    /**
     * 发补发类型
     *
     * 用户注册
     * 密码重置
     */
    private String sendType;

    /**
     * 收件人
     */
    private String recipient;

}
