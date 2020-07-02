package com.nicehancy.mix.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 脱敏数据类型枚举
 * <p>
 * <p/>
 *
 * @author hancaiyun
 * @since 2019/11/19 10:30
 **/
@Getter
@AllArgsConstructor
public enum SensitiveTypeEnum {

    /**
     * 姓名
     */
    NAME("NAME", "姓名"),

    /**
     * 11位手机号
     */
    MOBILE("MOBILE", "手机号"),

    /**
     * 身份证号
     */
    IDCARD("IDCARD", "身份证号"),

    /**
     * 邮箱
     */
    EMAIL("EMAIL", "邮箱"),
    ;

    /**
     * 枚举值
     */
    private final String code;

    /**
     * 描述
     */
    private final String desc;
}
