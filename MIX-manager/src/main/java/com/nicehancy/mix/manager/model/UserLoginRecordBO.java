package com.nicehancy.mix.manager.model;

import com.nicehancy.mix.manager.model.base.BaseBO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

/**
 * 用户登录记录表
 * <p>
 * <p/>
 *
 * @author hancaiyun
 * @since 2020/8/11 15:58
 **/
@Getter
@Setter
@ToString
public class UserLoginRecordBO extends BaseBO {

    /**
     * 用户编号
     */
    private String userNo;

    /**
     * 登录时间
     */
    private Date loginTime;

    /**
     * 登出时间
     */
    private Date logoutTime;
}
