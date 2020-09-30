package com.nicehancy.mix.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 分享标识枚举类
 * <p>
 * <p/>
 *
 * @author hancaiyun
 * @since 2020/9/27 17:39
 **/
@Getter
@AllArgsConstructor
public enum ShareFlagEnum {

    /**
     * 是
     */
    TRUE("TRUE", "是"),

    /**
     * 否
     */
    FALSE("FALSE", "否"),

    ;

    private final String code;

    private final String desc;
}
