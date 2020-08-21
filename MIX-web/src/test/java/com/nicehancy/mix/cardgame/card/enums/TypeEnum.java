package com.nicehancy.mix.cardgame.card.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * <p>
 * <p/>
 *
 * @author hancaiyun
 * @since 2020/8/21 13:57
 **/
@Getter
@AllArgsConstructor
public enum TypeEnum {

    ATTACK("ATTACK", "攻击型"),

    DEFENCE("DEFENCE", "防御型"),

    AUXILIARY("AUXILIARY", "辅助型"),

    HEALTH("HEALTH", "生命值型"),
    ;

    private final String code;

    private final String desc;
}
