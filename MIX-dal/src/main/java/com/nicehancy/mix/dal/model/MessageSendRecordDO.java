package com.nicehancy.mix.dal.model;

import com.nicehancy.mix.dal.model.base.BaseDO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 邮件发送记录DO模型
 * <p>
 * <p/>
 *
 * @author hancaiyun
 * @since 2020/4/28 12:31
 **/
@Getter
@Setter
@ToString
public class MessageSendRecordDO extends BaseDO {

    /**
     * 消息ID
     * 唯一ID
     */
    private String messageId;

    /**
     * 消息类型
     * MESSAGE-短信
     * EMAIL-邮件
     */
    private String messageType;

    /**
     * 消息名称
     */
    private String messageName;

    /**
     * 发件人
     */
    private String sender;

    /**
     * 收件人
     */
    private String recipient;

    /**
     * 发送结果
     * S-成功
     * F-失败
     */
    private String sendResult;

    /**
     * 原因
     */
    private String reason;

    /**
     * 消息内容
     */
    private String content;
}
