package com.nicehancy.mix.manager.model.card;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 牌BO
 * <p>
 * <p/>
 *
 * @author hancaiyun
 * @since 2020/8/24 16:21
 **/
@Getter
@Setter
@ToString
public class CardInfoBO {

    /**
     * 卡牌编码
     */
    private String cardCode;

    /**
     * 卡牌名称
     */
    private String cardName;

    /**
     * 卡牌类型
     */
    private String cardType;

    /**
     * 卡牌级别
     */
    private String cardLevel;

    /**
     * 卡牌状态
     */
    private String cardStatus;

    /**
     * 速度
     */
    private Integer speed;

    /**
     * 基础攻击力
     */
    private Integer baseAttack;

    /**
     * 当前攻击力
     */
    private Integer currentAttack;

    /**
     * 基础生命值
     */
    private Integer baseHealth;

    /**
     * 当前生命值
     */
    private Integer currentHealth;

    /**
     * 基础防御力
     */
    private Integer baseDefence;

    /**
     * 当前防御力
     */
    private Integer currentDefence;

    /**
     * 卡牌照片
     */
    private String cardPhoto;

    /**
     * 技能
     */
    private CardSkillInfoBO skill;
}
