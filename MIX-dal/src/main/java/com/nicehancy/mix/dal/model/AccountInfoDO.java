package com.nicehancy.mix.dal.model;

import com.nicehancy.mix.dal.model.base.BaseDO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.Document;

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
@Document("c_account_info")
public class AccountInfoDO extends BaseDO {

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
     * 备注
     */
    private String remark;
}
