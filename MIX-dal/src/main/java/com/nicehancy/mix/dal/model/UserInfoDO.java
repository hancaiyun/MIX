package com.nicehancy.mix.dal.model;

import com.nicehancy.mix.dal.model.base.BaseDO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * 用户信息BO
 * <p>
 * <p/>
 *
 * @author hancaiyun
 * @since 2019/11/8 9:06
 **/
@Getter
@Setter
@ToString
@Document("c_user_info")
public class UserInfoDO extends BaseDO {

    /**
     * 登陆号
     */
    private String userNo;

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
     * 男-MALE
     * 女-FEMALE
     */
    private String sex;

    /**
     * 密码
     * MD5密文
     */
    private String password;

    /**
     * 邮箱
     */
    private String email;

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
