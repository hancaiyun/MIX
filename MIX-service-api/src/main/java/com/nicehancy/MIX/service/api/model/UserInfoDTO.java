package com.nicehancy.MIX.service.api.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * <p>
 *     用户信息DTO
 * <p/>
 *
 * @author hancaiyun
 * @since 2019/4/2 16:23
 **/
@Getter
@Setter
@ToString(callSuper = true)
public class UserInfoDTO implements Serializable{

    /**
     * 序列化ID
     */
    private static final long serialVersionUID = -6977478057972485794L;

    /**
     * 用户编号/登陆号
     */
    private String userNo;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 昵称
     */
    private String nickName;

    /**
     * 密码
     * MD5密文
     */
    private String password;

    /**
     * 邮箱
     */
    private String eMail;

    /**
     * 权限编号
     */
    private String authCode;

    /**
     * 备注
     */
    private String remark;
}
