package com.nicehancy.mix.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * <p>
 *     业务类型枚举值
 * <p/>
 *
 * @author hancaiyun
 * @since 2020/6/9 16:52
 **/
@Getter
@AllArgsConstructor
public enum BusinessTypeEnum {

    /**
     * 用户注册
     */
    USER_REGISTER("USER_REGISTER", "用户注册"),

    /**
     * 密码重置
     */
    PASSWORD_RESET("PASSWORD_RESET", "密码重置"),
    ;

    private final String code;

    private final String desc;
}
