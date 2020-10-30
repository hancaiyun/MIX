package com.nicehancy.mix.manager.model;

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
@ToString
public class AccountAddReqBO {

    /**
     * 用户编号
     */
    private String userNo;

    /**
     * 地址
     */
    private String address;

    /**
     * 账号
     */
    private String account;

    /**
     * 账号类型
     */
    private String accountType;

    /**
     * 密码
     */
    private String password;

    /**
     * 备注
     */
    private String remark;
}
