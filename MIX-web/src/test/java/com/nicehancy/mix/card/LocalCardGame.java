package com.nicehancy.mix.card;

import com.nicehancy.mix.common.enums.card.*;
import com.nicehancy.mix.common.utils.UUIDUtil;
import com.nicehancy.mix.manager.model.card.CardInfoBO;
import com.nicehancy.mix.manager.model.card.CardSkillInfoBO;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

/**
 * 本地卡牌
 * @author ys
 * @date 2023/1/6
 */
@Slf4j
public class LocalCardGame {

    public static final Map<String, String> LOCAL_CACHE = new HashMap<>(16);

    //初始化并开始
    public static void main(String[] args) {
        initCard();
    }

    private static void initCard(){
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
        skillInfoBO2.setHurt(4350);
        skillInfoBO2.setCoolTurn(2);

        skillInfoBO2.setEffect(EffectEnum.DIZZY.getCode());
        skillInfoBO2.setSkillNo(UUIDUtil.createNoByUUId());
        card2.setSkill(skillInfoBO2);

        //开始
        battle(card1, card2);
    }

    public static void battle(CardInfoBO card1, CardInfoBO card2){

        for(int turn = 1; ; turn++) {

            //先手
            if (card1.getSpeed() > card2.getSpeed()) {
                hit(card1, card2);
                hit(card2, card1);
            } else {
                hit(card2, card1);
                hit(card1, card2);
            }

            //效果清算
            clearEffect(card1, card2.getSkill());
            clearEffect(card2, card1.getSkill());

            log.info("第{}回合结束, 斗士[{}]剩余生命值[{}], 当前状态[{}]; 斗士[{}]剩余生命值[{}], 当前状态[{}]", turn, card1.
                            getCardName(), card1.getCurrentHealth(), CardStatusEnum.expire(card1.getCardStatus()).getDesc(),
                    card2.getCardName(), card2.getCurrentHealth(), CardStatusEnum.expire(card2.getCardStatus()).getDesc());
            //判死
            if(!checkAlive(card1, card2)){
                //清除card1所有冷却， buff/debuff
                LOCAL_CACHE.remove(card1.getCardCode() + "SKILL");
                LOCAL_CACHE.remove(card1.getCardCode() + "BUFF");
                LOCAL_CACHE.remove(card1.getCardCode() + "DEBUFF");
                //清除card2所有冷却， buff/debuff
                LOCAL_CACHE.remove(card2.getCardCode() + "SKILL");
                LOCAL_CACHE.remove(card2.getCardCode() + "BUFF");
                LOCAL_CACHE.remove(card2.getCardCode() + "DEBUFF");
                break;
            }

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 进攻1——>2
     * @param card1             1
     * @param card2             2
     */
    private static void hit(CardInfoBO card1, CardInfoBO card2) {

        //卡牌1的技能
        CardSkillInfoBO cardSkillInfoBO = card1.getSkill();

        //状态是否可行动
        if(CardStatusEnum.NORMAL.getCode().equals(card1.getCardStatus())){
            //释放技能
            String coolTurn = LOCAL_CACHE.get(card1.getCardCode() + "SKILL");
            Integer hit;
            if(null == coolTurn){

                log.info("斗士[{}]释放了技能[{}], {}", card1.getCardName(), cardSkillInfoBO.getSkillName(),
                        EffectEnum.expire(cardSkillInfoBO.getEffect()).getDesc());
                //自身增益
                if(SkillTypeEnum.BUFF.getCode().equals(cardSkillInfoBO.getSkillType())){

                    //增攻
                    if(EffectEnum.ADD_ATTACK.getCode().equals(cardSkillInfoBO.getEffect())){
                        double currentAttack = card1.getBaseAttack() * EffectEnum.ADD_ATTACK.getValue();
                        card1.setCurrentAttack((int) currentAttack);
                    }

                    //增防
                    if(EffectEnum.ADD_DEFENCE.getCode().equals(cardSkillInfoBO.getEffect())){
                        double currentDefence = card1.getBaseDefence() * EffectEnum.ADD_DEFENCE.getValue();
                        card1.setCurrentDefence((int) currentDefence);
                    }

                    //增生命值
                    if(EffectEnum.ADD_HEALTH.getCode().equals(cardSkillInfoBO.getEffect())){
                        double currentHealth = card1.getBaseHealth() * EffectEnum.ADD_HEALTH.getValue();
                        card1.setCurrentHealth((int) currentHealth);
                    }

                    //增益维持回合数
                    LOCAL_CACHE.put(card2.getCardCode() + SkillTypeEnum.BUFF.getCode(),
                            String.valueOf(EffectEnum.expire(cardSkillInfoBO.getEffect()).getTurn()));
                }
                //敌方减益
                if(SkillTypeEnum.DEBUFF.getCode().equals(cardSkillInfoBO.getSkillType())){

                    //减功
                    if(EffectEnum.REDUCE_ATTACK.getCode().equals(cardSkillInfoBO.getEffect())){
                        double currentAttack = card2.getBaseAttack() * EffectEnum.REDUCE_ATTACK.getValue();
                        card2.setCurrentAttack((int) currentAttack);
                    }

                    //减防
                    if(EffectEnum.REDUCE_DEFENCE.getCode().equals(cardSkillInfoBO.getEffect())){
                        double currentDefence = card2.getBaseDefence() * EffectEnum.REDUCE_DEFENCE.getValue();
                        card2.setCurrentDefence((int) currentDefence);
                    }

                    //晕眩、冰冻、睡眠-限制行动+伤害
                    if(EffectEnum.expire(cardSkillInfoBO.getEffect()).getForbidden()){
                        card2.setCardStatus(cardSkillInfoBO.getEffect());
                        hit = cardSkillInfoBO.getHurt() - card2.getCurrentDefence();
                        //伤害计算, 技能伤害
                        log.info("斗士[{}]受到[{}]点技能伤害",card2.getCardName(), hit);
                        card2.setCurrentHealth(Math.max((card2.getCurrentHealth() - hit), 0));
                    }

                    //减益效果维持回合数
                    LOCAL_CACHE.put(card2.getCardCode() + SkillTypeEnum.DEBUFF.getCode(), String.valueOf(
                            EffectEnum.expire(cardSkillInfoBO.getEffect()).getTurn()));
                }

                //技能进入冷却
                LOCAL_CACHE.put(card1.getCardCode() + "SKILL", String.valueOf(cardSkillInfoBO.getCoolTurn()));
            }else{
                //普攻
                hit = card1.getCurrentAttack() - card2.getCurrentDefence();
                log.info("斗士[{}]受到[{}]点普攻伤害", card2.getCardName(), hit);
                card2.setCurrentHealth(Math.max((card2.getCurrentHealth() - hit), 0));
            }
        }
    }

    /**
     * 效果清算
     * @param card               牌
     * @param otherSkill         敌方技能
     */
    private static void clearEffect(CardInfoBO card, CardSkillInfoBO otherSkill) {

        //清算技能冷却、增益、减益效果持续回合数
        //牌1清算
        //清算技能
        CardSkillInfoBO selfSkill = card.getSkill();
        String turnStr = LOCAL_CACHE.get(card.getCardCode() + "SKILL");
        int turn;
        if(null != turnStr){
            turn = Integer.parseInt(turnStr);
            turn -- ;
            if(turn <= 0) {
                LOCAL_CACHE.remove(card.getCardCode() + "SKILL");
            }else{
                LOCAL_CACHE.put(card.getCardCode() + "SKILL", String.valueOf(turn));
            }
        }

        //清算buff，debuff
        String buffTurnStr = LOCAL_CACHE.get(card.getCardCode() + SkillTypeEnum.BUFF.getCode());
        String deBuffTurnStr = LOCAL_CACHE.get(card.getCardCode() + SkillTypeEnum.DEBUFF.getCode());
        int buffTurn;
        int debuffTurn;
        //清算buff
        if(null != buffTurnStr){
            buffTurn = Integer.parseInt(buffTurnStr);
            if(buffTurn == 0){
                //删除回合缓存
                LOCAL_CACHE.remove(card.getCardCode() + SkillTypeEnum.BUFF.getCode());
                //恢复之前的能力数值
                if(EffectEnum.ADD_DEFENCE.getCode().equals(selfSkill.getEffect())){
                    card.setCurrentDefence(card.getBaseDefence());
                }
                if(EffectEnum.ADD_ATTACK.getCode().equals(selfSkill.getEffect())){
                    card.setCurrentAttack(card.getBaseAttack());
                }
            }else{
                buffTurn--;
                LOCAL_CACHE.put(card.getCardCode() + SkillTypeEnum.BUFF.getCode(), String.valueOf(buffTurn));
            }
        }
        //清算debuff
        if(null != deBuffTurnStr){
            debuffTurn = Integer.parseInt(deBuffTurnStr);
            if(debuffTurn == 0){
                //删除回合缓存
                LOCAL_CACHE.remove(card.getCardCode() + SkillTypeEnum.DEBUFF.getCode());
                //恢复之前的能力数值
                if(EffectEnum.REDUCE_DEFENCE.getCode().equals(otherSkill.getEffect())){
                    card.setCurrentDefence(card.getBaseDefence());
                }
                if(EffectEnum.REDUCE_ATTACK.getCode().equals(otherSkill.getEffect())){
                    card.setCurrentAttack(card.getBaseAttack());
                }

                //清除限制行动状态
                if(EffectEnum.expire(otherSkill.getEffect()).getForbidden()){
                    card.setCardStatus(CardStatusEnum.NORMAL.getCode());
                }
            }else{
                debuffTurn--;
                LOCAL_CACHE.put(card.getCardCode() + SkillTypeEnum.DEBUFF.getCode(), String.valueOf(debuffTurn));
            }
        }
    }

    /**
     * 判死
     * @param card1             1
     * @param card2             2
     * @return                  结果
     */
    private static Boolean checkAlive(CardInfoBO card1, CardInfoBO card2) {

        if(card1.getCurrentHealth() <= 0){
            log.info("斗士[{}]已死亡， 战斗结束", card1.getCardName());
            return false;
        }

        if(card2.getCurrentHealth() <= 0){
            log.info("斗士[{}]已死亡， 战斗结束", card2.getCardName());
            return false;
        }

        return true;
    }
}
