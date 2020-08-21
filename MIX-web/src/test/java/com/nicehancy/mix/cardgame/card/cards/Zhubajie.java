package com.nicehancy.mix.cardgame.card.cards;

import com.nicehancy.mix.cardgame.card.enums.BuffEnum;
import com.nicehancy.mix.cardgame.card.enums.StatusEnum;
import com.nicehancy.mix.cardgame.card.enums.TypeEnum;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * <p/>
 *
 * @author hancaiyun
 * @since 2020/8/21 14:19
 **/
@Getter
@Setter
public class Zhubajie {

    //类型
    private final String type = TypeEnum.HEALTH.getCode();

    //速度
    private final Integer speed = 40;

    //攻击力
    private final Integer attack = 95;

    //生命值
    private Integer health = 1065;

    //防御力
    private final Integer defence = 50;

    //状态
    private String status = StatusEnum.NORMAL.getCode();

    //技能
    private final BuffEnum skill = BuffEnum.DEFENCE;

    //技能冷却状态 0-可以正常释放，不在冷却状态
    private Integer leftRound = 0;
}
