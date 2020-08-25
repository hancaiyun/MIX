package com.nicehancy.mix.manager.model.card;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 卡牌技能
 * <p>
 * <p/>
 *
 * @author hancaiyun
 * @since 2020/8/24 16:45
 **/
@Getter
@Setter
@ToString
public class CardSkillInfoBO {

    /**
     * 技能编号
     */
    private String skillNo;

    /**
     * 技能类型
     */
    private String skillType;

    /**
     * 技能名称
     */
    private String skillName;

    /**
     * 技能描述
     */
    private String skillDesc;

    /**
     * 造成伤害
     */
    private Integer hurt;

    /**
     * 效果
     */
    private String effect;

    /**
     * 范围
     */
    private String scope;

    /**
     * 冷却回合数
     */
    private Integer coolTurn;
}
