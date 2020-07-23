package com.nicehancy.mix.manager.model;

import com.nicehancy.mix.manager.model.base.BaseBO;
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
     * 登陆号（邮箱）
     */
    private String loginNo;

    /**
     * 昵称
     */
    private String nickName;

    /**
     * 头像
     */
    private String headCopy;

    /**
     * 性别
     *
     * MALE-男
     * FEMALE-女
     */
    private String sex;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 密码
     * MD5密文
     */
    private String password;

    /**
     * 权限编号
     */
    private String authCode;

    /**
     * 状态
     */
    private String status;

    /**
     * 备注
     */
    private String remark;
}
