package com.nicehancy.mix.common.enums.card;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * <p>
 * <p/>
 *
 * @author hancaiyun
 * @since 2020/8/24 17:37
 **/
@Getter
@AllArgsConstructor
public enum SkillTypeEnum {

    BUFF("BUFF", "增益"),

    DEBUFF("DEBUFF", "减益"),

    ;

    private final String code;

    private final String desc;
}
