package com.nicehancy.mix.common.enums.card;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 效果枚举类
 * <p>
 * <p/>
 *
 * @author hancaiyun
 * @since 2020/8/24 17:45
 **/
@Getter
@AllArgsConstructor
public enum EffectEnum {

    DIZZY("DIZZY", "晕眩敌人", true, 1,null),

    FREEZE("FREEZE", "冰冻敌人", true, 1,null),

    SLEEP("SLEEP", "使敌人陷入睡眠", true, 1,null),

    ADD_ATTACK("ADD_ATTACK", "增加30%攻击力", false, 0,1.3),

    REDUCE_ATTACK("REDUCE_ATTACK", "降低30%攻击力", false, 0,0.7),

    ADD_DEFENCE("ADD_DEFENCE", "增加30%防御力", false, 0,1.3),

    REDUCE_DEFENCE("REDUCE_DEFENCE", "降低30%防御力", false, 0,0.7),

    ADD_HEALTH("ADD_HEALTH", "恢复自身10%生命值", false, 0,1.1),

    UNKNOWN("UNKNOWN", "未知", false, 0,null),
    ;

    private final String code;

    private final String desc;

    private final Boolean forbidden;

    private final Integer turn;

    private final Double value;

    public static EffectEnum expire(String code){
        for(EffectEnum effectEnum : EffectEnum.values()){
            if(effectEnum.getCode().equals(code)){
                return effectEnum;
            }
        }
        return UNKNOWN;
    }
}
