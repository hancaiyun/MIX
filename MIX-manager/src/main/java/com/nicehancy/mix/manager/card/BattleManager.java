package com.nicehancy.mix.manager.card;

import com.nicehancy.mix.common.enums.card.CardStatusEnum;
import com.nicehancy.mix.common.enums.card.EffectEnum;
import com.nicehancy.mix.common.enums.card.SkillTypeEnum;
import com.nicehancy.mix.manager.model.card.CardInfoBO;
import com.nicehancy.mix.manager.model.card.CardSkillInfoBO;
import com.nicehancy.mix.manager.redis.RedisManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 战争
 * <p>
 * <p/>
 *
 * @author hancaiyun
 * @since 2020/8/24 16:42
 **/
@Slf4j
@Component
public class BattleManager {

    @Autowired
    private RedisManager redisManager;

    /**
     * battle
     * @param card1             牌1
     * @param card2             牌2
     */
    public void battle(CardInfoBO card1, CardInfoBO card2){

        CardSkillInfoBO cardSkill1 = card1.getSkill();
        CardSkillInfoBO cardSkill2 = card2.getSkill();

        for(int turn = 1; ; turn++) {

            //速度快的先手
            if (card1.getSpeed() > card2.getSpeed()) {
                hit(card1, card2, cardSkill1);
                hit(card2, card1, cardSkill2);
            } else {
                hit(card2, card1, cardSkill2);
                hit(card1, card2, cardSkill1);
            }

            //效果清算
            clearEffect(card1, cardSkill1, cardSkill2);
            clearEffect(card2, cardSkill2, cardSkill1);

            log.info("第{}回合结束, 英雄[{}]剩余生命值[{}], 当前状态[{}]; 英雄[{}]剩余生命值[{}], 当前状态[{}]", turn, card1.
                            getCardName(), card1.getCurrentHealth(), CardStatusEnum.expire(card1.getCardStatus()).getDesc(),
                    card2.getCardName(), card2.getCurrentHealth(), CardStatusEnum.expire(card2.getCardStatus()).getDesc());
            //判死
            if(!checkAlive(card1, card2)){
                //清除所有冷却， buff/debuff
                redisManager.deleteObject(card1.getCardCode() + "SKILL");
                redisManager.deleteObject(card1.getCardCode() + "BUFF");
                redisManager.deleteObject(card1.getCardCode() + "DEBUFF");

                //清除所有冷却， buff/debuff
                redisManager.deleteObject(card2.getCardCode() + "SKILL");
                redisManager.deleteObject(card2.getCardCode() + "BUFF");
                redisManager.deleteObject(card2.getCardCode() + "DEBUFF");

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
     * 效果清算
     * @param card               牌
     * @param selfSkill          自我技能
     * @param otherSkill         敌方技能
     */
    private void clearEffect(CardInfoBO card, CardSkillInfoBO selfSkill, CardSkillInfoBO otherSkill) {

        //清算技能冷却、增益、减益效果持续回合数
        //牌1清算
        //清算技能
        String turnStr = redisManager.queryObjectByKey(card.getCardCode() + "SKILL");
        int turn;
        if(null != turnStr){
            turn = Integer.parseInt(turnStr);
            turn -- ;
            if(turn <= 0) {
                redisManager.deleteObject(card.getCardCode() + "SKILL");
            }else{
                redisManager.insertObject(turn, card.getCardCode() + "SKILL", 3600);
            }
        }

        //清算buff，debuff
        String buffTurnStr = redisManager.queryObjectByKey(card.getCardCode() + SkillTypeEnum.BUFF.getCode());
        String debuffTurnStr = redisManager.queryObjectByKey(card.getCardCode() + SkillTypeEnum.DEBUFF.getCode());
        int buffTurn;
        int debuffTurn;
        if(null != buffTurnStr){
            buffTurn = Integer.parseInt(buffTurnStr);
            if(buffTurn == 0){
                //删除回合缓存
                redisManager.deleteObject(card.getCardCode() + SkillTypeEnum.BUFF.getCode());
                //恢复之前的数值
                if(EffectEnum.ADD_DEFENCE.getCode().equals(selfSkill.getEffect())){
                    card.setCurrentDefence(card.getBaseDefence());
                }
                if(EffectEnum.ADD_ATTACK.getCode().equals(selfSkill.getEffect())){
                    card.setCurrentAttack(card.getBaseAttack());
                }
            }else{
                buffTurn--;
                redisManager.insertObject(buffTurn, card.getCardCode() + SkillTypeEnum.BUFF.getCode(),
                        3600);
            }
        }
        if(null != debuffTurnStr){
            debuffTurn = Integer.parseInt(debuffTurnStr);
            if(debuffTurn == 0){
                //删除回合缓存
                redisManager.deleteObject(card.getCardCode() + SkillTypeEnum.DEBUFF.getCode());
                //恢复之前的数值
                if(EffectEnum.REDUCE_DEFENCE.getCode().equals(otherSkill.getEffect())){
                    card.setCurrentDefence(card.getBaseDefence());
                }
                if(EffectEnum.ADD_ATTACK.getCode().equals(otherSkill.getEffect())){
                    card.setCurrentAttack(card.getBaseAttack());
                }

                //清除限制行动状态
                if(EffectEnum.expire(otherSkill.getEffect()).getForbidden()){
                    card.setCardStatus(CardStatusEnum.NORMAL.getCode());
                }
            }else{
                debuffTurn--;
                redisManager.insertObject(debuffTurn, card.getCardCode() + SkillTypeEnum.DEBUFF.getCode(),
                        3600);
            }
        }
    }

    /**
     * 判死
     * @param card1             1
     * @param card2             2
     * @return                  结果
     */
    private Boolean checkAlive(CardInfoBO card1, CardInfoBO card2) {

        if(card1.getCurrentHealth() <= 0){
            log.info("英雄[{}]已死亡， 战斗结束", card1.getCardName());
            return false;
        }

        if(card2.getCurrentHealth() <= 0){
            log.info("英雄[{}]已死亡， 战斗结束", card2.getCardName());
            return false;
        }

        return true;
    }

    /**
     * 进攻1——>2
     * @param card1             1
     * @param card2             2
     * @param cardSkillInfoBO   技能BO
     */
    private void hit(CardInfoBO card1, CardInfoBO card2, CardSkillInfoBO cardSkillInfoBO) {

        //状态是否可行动
        if(CardStatusEnum.NORMAL.getCode().equals(card1.getCardStatus())){
            //释放技能
            String coolTurn = redisManager.queryObjectByKey(card1.getCardCode() + "SKILL");
            Integer hit;
            if(null == coolTurn){

                log.info("英雄[{}]释放了技能[{}], {}", card1.getCardName(), cardSkillInfoBO.getSkillName(),
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
                    redisManager.insertObject(EffectEnum.expire(cardSkillInfoBO.getEffect()).getTurn(),
                            card2.getCardCode() + SkillTypeEnum.BUFF.getCode(), 3600);
                }
                //敌方减益
                if(SkillTypeEnum.DEBUFF.getCode().equals(cardSkillInfoBO.getSkillType())){

                    //减功
                    if(EffectEnum.REDUCE_ATTACK.getCode().equals(cardSkillInfoBO.getEffect())){
                        double currentAttack = card2.getBaseAttack() * EffectEnum.REDUCE_ATTACK.getValue();
                        card1.setCurrentAttack((int) currentAttack);
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
                        log.info("英雄[{}]受到[{}]点技能伤害",card2.getCardName(), hit);
                        card2.setCurrentHealth(card2.getCurrentHealth() - hit);
                    }

                    //减益效果维持回合数
                    redisManager.insertObject(EffectEnum.expire(cardSkillInfoBO.getEffect()).getTurn(),
                            card2.getCardCode() + SkillTypeEnum.DEBUFF.getCode(), 3600);
                }

                //技能进入冷却
                redisManager.insertObject(cardSkillInfoBO.getCoolTurn(),card1.getCardCode() + "SKILL", 3600);
            }else{
                //普攻
                hit = card1.getCurrentAttack() - card2.getCurrentDefence();
                log.info("英雄[{}]受到[{}]点普攻伤害", card2.getCardName(), hit);
                card2.setCurrentHealth(card2.getCurrentHealth() - hit);
            }
        }
    }
}
