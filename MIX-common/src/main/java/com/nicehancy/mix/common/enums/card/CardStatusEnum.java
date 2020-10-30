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

    /**
     * 正常
     */
    NORMAL("NORMAL", "正常"),

    /**
     * 晕眩
     */
    DIZZY("DIZZY", "晕眩"),

    /**
     * 冰冻
     */
    FREEZE("FREEZE", "冰冻"),

    /**
     * 睡眠
     */
    SLEEP("SLEEP", "睡眠"),

    /**
     * 死亡
     */
    DIED("DIED", "死亡"),
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
