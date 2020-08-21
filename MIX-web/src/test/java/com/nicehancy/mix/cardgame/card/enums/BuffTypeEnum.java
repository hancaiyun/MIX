package com.nicehancy.mix.cardgame.card.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * <p>
 * <p/>
 *
 * @author hancaiyun
 * @since 2020/8/21 16:58
 **/
@Getter
@AllArgsConstructor
public enum BuffTypeEnum {

    BUFF("BUFF", "增益"),

    DEBUFF("DEBUFF", "减益"),

    ;

    private final String code;

    private final String desc;
}
