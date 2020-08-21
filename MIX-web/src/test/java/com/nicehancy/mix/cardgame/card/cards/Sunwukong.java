package com.nicehancy.mix.cardgame.card.cards;

import com.nicehancy.mix.cardgame.card.enums.BuffEnum;
import com.nicehancy.mix.cardgame.card.enums.StatusEnum;
import com.nicehancy.mix.cardgame.card.enums.TypeEnum;
import lombok.Getter;
import lombok.Setter;

/**
 * 孙悟空
 * <p>
 * <p/>
 *
 * @author hancaiyun
 * @since 2020/8/21 13:52
 **/
@Getter
@Setter
public class Sunwukong {

    //类型
    private final String type = TypeEnum.ATTACK.getCode();

    //速度
    private final Integer speed = 45;

    //攻击力
    private final Integer attack = 110;

    //生命值
    private Integer health = 950;

    //防御力
    private final Integer defence = 40;

    //状态
    private String status = StatusEnum.NORMAL.getCode();

    //技能
    private final BuffEnum skill = BuffEnum.DIZZY;

    //技能冷却状态 0-可以正常释放，不在冷却状态
    private Integer leftRound = 0;

}
