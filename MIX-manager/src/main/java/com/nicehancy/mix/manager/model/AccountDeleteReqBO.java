package com.nicehancy.mix.manager.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * <p>
 * <p/>
 *
 * @author hancaiyun
 * @since 2020/7/10 16:09
 **/
@Getter
@Setter
@ToString
public class AccountDeleteReqBO {

    /**
     * 用户编号
     */
    private String userNo;

    /**
     * 账号ID
     */
    private Long id;
}
