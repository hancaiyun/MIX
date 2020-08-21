package com.nicehancy.mix.cardgame.card.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * <p>
 * <p/>
 *
 * @author hancaiyun
 * @since 2020/8/21 16:14
 **/
@Getter
@AllArgsConstructor
public enum BuffEnum {

    DIZZY("DIZZY", BuffTypeEnum.DEBUFF.getCode(), 20,"雷霆一击", "眩晕敌人,使之无法行动", "单体", 1),

    FREEZE("FREEZE", BuffTypeEnum.DEBUFF.getCode(), 5,"凌冬将至", "范围冰冻，丧失行动力", "范围", 1),

    SLEEP("SLEEP", BuffTypeEnum.DEBUFF.getCode(), 5,"催眠曲", "使敌人陷入昏睡", "范围", 1),

    DEFENCE("DEFENCE", BuffTypeEnum.BUFF.getCode(), 0,"勇者无敌", "增加自身30%防御力", "自身", 2),

    ADD_BLOOD("ADD_BLOOD", BuffTypeEnum.BUFF.getCode(), 0,"恢复", "恢复自身10%最大生命值", "自身", 1),

    ;

    private final String code;

    private final String type;

    private final Integer elseAttack;

    private final String desc;

    private final String effect;

    private final String scope;

    private final Integer rounds;

}
