package com.nicehancy.mix.service.api.model.result;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 账号信息DO
 * <p>
 * <p/>
 *
 * @author hancaiyun
 * @since 2020/7/10 14:38
 **/
@Getter
@Setter
@ToString
public class AccountInfoDTO {

    /**
     * id
     */
    private Long id;

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
     * 密码
     */
    private String password;

    /**
     * 账号类型
     */
    private String accountType;

    /**
     * 备注
     */
    private String remark;

    /**
     * 更新时间
     */
    private String updatedAt;
}
