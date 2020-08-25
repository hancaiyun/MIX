package com.nicehancy.mix.dal.model.card;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.Document;

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
@Document("c_card_skill_info")
public class CardSkillInfoDO {

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
     * 持续回合数
     */
    private Integer turn;
}
