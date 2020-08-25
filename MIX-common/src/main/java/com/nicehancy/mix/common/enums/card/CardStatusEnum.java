package com.nicehancy.mix.common.enums.card;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * <p>
 * <p/>
 *
 * @author hancaiyun
 * @since 2020/8/21 14:05
 **/
@Getter
@AllArgsConstructor
public enum CardStatusEnum {

    NORMAL("NORMAL", "正常"),

    DIZZY("DIZZY", "晕眩"),

    FREEZE("FREEZE", "冰冻"),

    SLEEP("SLEEP", "睡眠"),
    ;

    private final String code;

    private final String desc;

    public static CardStatusEnum expire(String code){
        for(CardStatusEnum cardStatusEnum : CardStatusEnum.values()){
            if(cardStatusEnum.getCode().equals(code)){
                return cardStatusEnum;
            }
        }

        return NORMAL;
    }
}
