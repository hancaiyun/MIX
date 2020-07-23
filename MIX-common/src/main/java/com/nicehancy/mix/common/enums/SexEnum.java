package com.nicehancy.mix.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 性别枚举类
 * <p>
 * <p/>
 *
 * @author hancaiyun
 * @since 2020/7/23 15:40
 **/
@Getter
@AllArgsConstructor
public enum SexEnum {

    /**
     * 男
     */
    MALE("MALE", "男"),

    /**
     * 女
     */
    FEMALE("FEMALE", "女")

    ;

    private final String code;

    private final String desc;
}
