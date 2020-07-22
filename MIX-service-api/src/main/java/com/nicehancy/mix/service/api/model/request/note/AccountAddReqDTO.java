package com.nicehancy.mix.service.api.model.request.note;

import com.nicehancy.mix.service.api.model.base.BaseReqDTO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import net.sf.oval.constraint.NotBlank;
import net.sf.oval.constraint.NotNull;

/**
 * <p>
 * <p/>
 *
 * @author hancaiyun
 * @since 2020/7/10 16:09
 **/
@Getter
@Setter
@ToString(callSuper = true)
public class AccountAddReqDTO extends BaseReqDTO {

    /**
     * 序列化ID
     */
    private static final long serialVersionUID = 8399300313893307803L;

    /**
     * 用户编号
     */
    @NotNull(message = "用户编号不允许为空")
    @NotBlank(message = "用户编号不允许为空")
    private String userNo;

    /**
     * 地址
     */
    @NotNull(message = "地址不允许为空")
    @NotBlank(message = "地址不允许为空")
    private String address;

    /**
     * 账号
     */
    @NotNull(message = "账号不允许为空")
    @NotBlank(message = "账号不允许为空")
    private String account;

    /**
     * 密码
     */
    @NotNull(message = "密码不允许为空")
    @NotBlank(message = "密码不允许为空")
    private String password;

    /**
     * 备注
     */
    private String remark;
}
