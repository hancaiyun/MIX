package com.nicehancy.mix.manager.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import java.util.Date;

/**
 * 消息发送记录DO模型
 * <p>
 * <p/>
 *
 * @author hancaiyun
 * @since 2020/4/28 12:31
 **/
@Getter
@Setter
@ToString
public class MessageSendRecordBO {

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

    /**
     * 创建时间
     */
    private Date createdAt;

    /**
     * 创建人
     */
    private String createdBy;

    /**
     * 更新时间
     */
    private Date updatedAt;

    /**
     * 更新人
     */
    private String updatedBy;
}
