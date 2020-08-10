package com.nicehancy.mix.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 系统类型枚举
 * <p>
 * <p/>
 *
 * @author hancaiyun
 * @since 2020/8/10 16:11
 **/
@Getter
@AllArgsConstructor
public enum SystemTypeEnum {

    /**
     * win
     */
    WINDOWS("WINDOWS", "Windows"),

    /**
     * linux
     */
    LINUX("LINUX", "Linux"),
    ;

    private final String code;

    private final String desc;
}
