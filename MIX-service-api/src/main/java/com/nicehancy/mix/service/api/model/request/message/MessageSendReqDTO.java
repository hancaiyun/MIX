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
@ToString(callSuper = true)
public class MessageSendReqDTO extends BaseReqDTO {

    private static final long serialVersionUID = -1808433468778452431L;

    /**
     * 业务类型
     *
     * 密码重置
     * 登陆注册
     */
    private String businessType;

    /**
     * 发件人
     *
     */
    private String sender;

    /**
     * 收件人
     */
    private String recipient;

    /**
     * 补发原因
     */
    private String reason;

}
