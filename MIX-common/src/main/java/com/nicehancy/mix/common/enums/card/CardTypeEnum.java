package com.nicehancy.mix.common.enums.card;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 牌类型枚举类
 * <p>
 * <p/>
 *
 * @author hancaiyun
 * @since 2020/8/25 11:17
 **/
@Getter
@AllArgsConstructor
public enum CardTypeEnum {

    /**
     * 攻击型
     */
    ATTACK("ATTACK", "攻击型"),

    /**
     * 防御型
     */
    DEFENCE("DEFENCE", "防御型"),

    /**
     * 辅助型
     */
    AUXILIARY("AUXILIARY", "辅助型"),

    /**
     * 生命值型
     */
    HEALTH("HEALTH", "生命值型")
    ;

    private final String code;

    private final String desc;
}
