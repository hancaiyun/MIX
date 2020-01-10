package com.nicehancy.MIX.dal.model;

import com.nicehancy.MIX.dal.model.base.BaseDO;
import lombok.Getter;
import lombok.Setter;
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
@Document("c_user_info")
public class UserInfoDO extends BaseDO {

    /**
     * 登陆号（邮箱）
     */
    private String userNo;

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
    private String email;

    /**
     * 权限编号
     */
    private String authCode;

    /**
     * 备注
     */
    private String remark;

}
