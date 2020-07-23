package com.nicehancy.mix.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 用户等级枚举
 * <p>
 * <p/>
 *
 * @author hancaiyun
 * @since 2020/7/23 15:34
 **/
@Getter
@AllArgsConstructor
public enum UserLevelEnum {

    /**
     * 普通用户
     */
    COMMON("COMMON", "普通用户"),

    /**
     * vip
     */
    VIP("VIP", "VIP"),
    ;

    private final String code;

    private final String desc;
}
