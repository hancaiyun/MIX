package com.nicehancy.mix.dal.model;

import com.nicehancy.mix.dal.model.base.BaseDO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.Document;
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
@Document("c_user_login_record")
public class UserLoginRecordDO extends BaseDO {

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
