package com.nicehancy.mix.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 权限编码枚举类
 * <p>
 * <p/>
 *
 * @author hancaiyun
 * @since 2020/7/23 15:28
 **/
@Getter
@AllArgsConstructor
public enum AuthCodeEnum {

    /**
     * 超级管理员
     */
    SUPPER_ADMIN("SUPPER_ADMIN", "超级管理员"),

    /**
     * 普通管理员
     */
    ADMIN("ADMIN", "普通管理员"),

    /**
     * 用户
     */
    USER("USER", "用户"),

    ;
    private final String code;

    private final String desc;
}
