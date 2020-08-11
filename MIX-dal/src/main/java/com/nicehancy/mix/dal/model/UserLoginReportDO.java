package com.nicehancy.mix.dal.model;

import com.nicehancy.mix.dal.model.base.BaseDO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

/**
 * 用户登录日报表
 * <p>
 * <p/>
 *
 * @author hancaiyun
 * @since 2020/8/11 16:03
 **/
@Getter
@Setter
@ToString
@Document("c_user_login_report")
public class UserLoginReportDO extends BaseDO {

    /**
     * 用户编号
     */
    private String userNo;

    /**
     * 昵称
     */
    private String nickName;

    /**
     * 登录次数
     */
    private Date loginCount;

    /**
     * 登录日期
     */
    private Date loginDate;
}
