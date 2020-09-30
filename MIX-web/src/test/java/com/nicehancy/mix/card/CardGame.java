package com.nicehancy.mix.card;

import com.nicehancy.mix.base.BaseSpringTest;
import com.nicehancy.mix.common.enums.card.*;
import com.nicehancy.mix.common.utils.UUIDUtil;
import com.nicehancy.mix.manager.card.BattleManager;
import com.nicehancy.mix.manager.model.card.CardInfoBO;
import com.nicehancy.mix.manager.model.card.CardSkillInfoBO;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * battle
 * <p>
 * <p/>
 *
 * @author hancaiyun
 * @since 2020/8/25 11:11
 **/
@Slf4j
public class CardGame extends BaseSpringTest {

    @Autowired
    private BattleManager battleManager;

    @Test
    public void battle(){

        CardInfoBO card1 = new CardInfoBO();
        card1.setCardCode("H6000000332591011");
        card1.setCardStatus(CardStatusEnum.NORMAL.getCode());
        card1.setCardName("寒冰锤神");
        card1.setCardType(CardTypeEnum.DEFENCE.getCode());
        card1.setCardLevel(CardLevelEnum.GOLD.getCode());
        card1.setBaseAttack(815);
        card1.setBaseDefence(1421);
        card1.setBaseHealth(20145);
        card1.setCurrentAttack(815);
        card1.setCurrentDefence(1421);
        card1.setCurrentHealth(20145);
        card1.setSpeed(94);
        CardSkillInfoBO skillInfoBO1 = new CardSkillInfoBO();
        skillInfoBO1.setSkillName("凌冬将至");
        skillInfoBO1.setSkillDesc("召唤寒冬风暴，【冰冻】范围内的敌人");
        skillInfoBO1.setSkillType(SkillTypeEnum.DEBUFF.getCode());
        //技能伤害
        skillInfoBO1.setHurt(3750);
        skillInfoBO1.setCoolTurn(3);
        skillInfoBO1.setEffect(EffectEnum.FREEZE.getCode());
        skillInfoBO1.setSkillNo(UUIDUtil.createNoByUUId());
        card1.setSkill(skillInfoBO1);

        CardInfoBO card2 = new CardInfoBO();
        card2.setCardCode("L6000000667354571");
        card2.setCardStatus(CardStatusEnum.NORMAL.getCode());
        card2.setCardName("罗巴");
        card2.setCardType(CardTypeEnum.ATTACK.getCode());
        card2.setCardLevel(CardLevelEnum.GOLD.getCode());
        card2.setBaseAttack(1619);
        card2.setBaseDefence(694);
        card2.setBaseHealth(18990);
        card2.setCurrentAttack(1619);
        card2.setCurrentDefence(694);
        card2.setCurrentHealth(18990);
        card2.setSpeed(87);
        CardSkillInfoBO skillInfoBO2 = new CardSkillInfoBO();
        skillInfoBO2.setSkillName("无上力量");
        skillInfoBO2.setSkillDesc("对一名敌人进行攻击， 并且【眩晕】敌人");
        skillInfoBO2.setSkillType(SkillTypeEnum.DEBUFF.getCode());
        //技能伤害
        skillInfoBO2.setHurt(7780);
        skillInfoBO2.setCoolTurn(2);

        skillInfoBO2.setEffect(EffectEnum.DIZZY.getCode());
        skillInfoBO2.setSkillNo(UUIDUtil.createNoByUUId());
        card2.setSkill(skillInfoBO2);

        battleManager.battle(card1, card2);
    }
}
