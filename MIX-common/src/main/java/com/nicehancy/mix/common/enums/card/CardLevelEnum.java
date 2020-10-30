package com.nicehancy.mix.common.enums.card;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 品质枚举类
 * <p>
 * <p/>
 *
 * @author hancaiyun
 * @since 2020/8/25 11:22
 **/
@Getter
@AllArgsConstructor
public enum CardLevelEnum {

    /**
     * 蓝色
     */
    BLUE("BLUE", "蓝色"),

    /**
     * 紫色
     */
    PURPLE("PURPLE", "紫色"),

    /**
     * 金色
     */
    GOLD("GOLD", "金色"),

    ;

    private final String code;

    private final String desc;
}
