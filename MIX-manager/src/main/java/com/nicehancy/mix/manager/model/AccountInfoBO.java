package com.nicehancy.mix.manager.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import java.util.Date;

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
public class AccountInfoBO {

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
     * 状态
     */
    private String status;

    /**
     * 账号类型
     */
    private String accountType;

    /**
     * 备注
     */
    private String remark;

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

    /**
     * 原因
     */
    private String reason;
}
