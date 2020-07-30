package com.nicehancy.mix.manager.model;

import lombok.Getter;
import lombok.Setter;
import net.sf.oval.constraint.MemberOf;

/**
 * <p>
 * <p/>
 *
 * @author hancaiyun
 * @since 2020/7/10 16:09
 **/
@Getter
@Setter
public class AccountQueryReqBO {

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
     * 账号类型
     */
    private String accountType;

    /**
     * 当前页
     */
    private Integer currentPage;

    /**
     * 每页条目数
     */
    private Integer pageSize;
}
