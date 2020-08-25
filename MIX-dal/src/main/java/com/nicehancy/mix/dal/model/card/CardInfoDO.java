package com.nicehancy.mix.dal.model.card;

import com.nicehancy.mix.dal.model.base.BaseDO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * 牌DO
 * <p>
 * <p/>
 *
 * @author hancaiyun
 * @since 2020/8/24 16:21
 **/
@Getter
@Setter
@ToString
@Document("c_card_info")
public class CardInfoDO extends BaseDO {

    /**
     * 卡牌编号
     */
    private String cardNo;

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
     * 技能编号
     */
    private String skillNo;

    /**
     * 卡牌状态
     */
    private String cardStatus;

    /**
     * 速度
     */
    private Integer speed;

    /**
     * 攻击
     */
    private Integer attack;

    /**
     * 最大生命值
     */
    private Integer maxHealth;

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
}
