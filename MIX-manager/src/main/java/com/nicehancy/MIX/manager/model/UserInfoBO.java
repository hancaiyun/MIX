package com.nicehancy.MIX.manager.model;

import com.nicehancy.MIX.manager.model.base.BaseBO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 用户信息BO
 * <p>
 * <p/>
 *
 * @author hancaiyun
 * @since 2020/1/2 20:06
 **/
@Getter
@Setter
@ToString
public class UserInfoBO extends BaseBO{
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
