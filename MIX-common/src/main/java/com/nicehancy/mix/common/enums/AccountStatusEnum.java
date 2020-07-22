package com.nicehancy.mix.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 账户信息状态枚举类
 * <p>
 * <p/>
 *
 * @author hancaiyun
 * @since 2020/6/17 10:18
 **/
@Getter
@AllArgsConstructor
public enum AccountStatusEnum {

    /**
     * 正常
     */
    NORMAL("NORMAL", "正常"),

    /**
     * 已删除
     */
    DELETE("DELETE", "已删除"),
    ;

    private final String code;

    private final String desc;
}
