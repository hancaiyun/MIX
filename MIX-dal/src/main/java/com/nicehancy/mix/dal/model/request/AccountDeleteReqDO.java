package com.nicehancy.mix.dal.model.request;

import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * <p/>
 *
 * @author hancaiyun
 * @since 2020/7/10 16:09
 **/
@Getter
@Setter
public class AccountDeleteReqDO {

    /**
     * 用户编号
     */
    private String userNo;

    /**
     * 账号ID
     */
    private Long id;
}
