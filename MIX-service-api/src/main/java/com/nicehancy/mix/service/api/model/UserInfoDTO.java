package com.nicehancy.mix.service.api.model;

import com.nicehancy.mix.service.api.model.base.BaseReqDTO;
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
public class UserInfoDTO extends BaseReqDTO implements Serializable{

    /**
     * 序列化ID
     */
    private static final long serialVersionUID = -6977478057972485794L;

    /**
     * 登陆号
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
     * 邮箱
     */
    private String email;

    /**
     * 性别
     *
     * MALE-男
     * FEMALE-女
     */
    private String sex;

    /**
     * 状态
     */
    private String status;

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
     * 备注
     */
    private String remark;

    /**
     * 验证码
     */
    private String verCode;
}
