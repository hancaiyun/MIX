package com.nicehancy.mix.cardgame.card.way;

import com.nicehancy.mix.cardgame.card.cards.Sunwukong;
import com.nicehancy.mix.cardgame.card.cards.Zhubajie;
import com.nicehancy.mix.cardgame.card.enums.StatusEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.Assert;

/**
 * <p>
 * <p/>
 *
 * @author hancaiyun
 * @since 2020/8/21 14:13
 **/
@Slf4j
public class Fight {

    public static void start(Sunwukong sunwukong, Zhubajie zhubajie){

      log.info("回合开始...");

      for(int i=1;;i++){

          //先手
          if(sunwukong.getSpeed() > zhubajie.getSpeed()){

              if(StatusEnum.NORMAL.getCode().equals(sunwukong.getStatus())) {

                  int hit = sunwukong.getAttack() - zhubajie.getDefence();
                  if(sunwukong.getLeftRound() == 0){
                    log.info("孙悟空释放了技能{}, 造成额外{}伤害", sunwukong.getSkill().getDesc(), sunwukong.getSkill().getElseAttack());
                      hit+=sunwukong.getSkill().getElseAttack();
                      zhubajie.setStatus(StatusEnum.DIZZY.getCode());
                      sunwukong.setLeftRound(2);
                  }
                  Integer leftHealthForZhu = zhubajie.getHealth() - hit;
                  zhubajie.setHealth(leftHealthForZhu);
                  log.info("猪八戒受到{}点伤害, 剩余生命值：{}", sunwukong.getAttack() - zhubajie.getDefence(), leftHealthForZhu);
                  Assert.isTrue(checkAlive(zhubajie), "猪八戒已死");
              }

              try {
                  Thread.sleep(1000);
              } catch (InterruptedException e) {
                  e.printStackTrace();
              }


              if(StatusEnum.NORMAL.getCode().equals(zhubajie.getStatus())) {
                  Integer leftHealthForSun = sunwukong.getHealth() - (zhubajie.getAttack() - sunwukong.getDefence());
                  sunwukong.setHealth(leftHealthForSun);
                  log.info("孙悟空受到{}点伤害, 剩余生命值：{}", zhubajie.getAttack() - sunwukong.getDefence(), leftHealthForSun);
                  Assert.isTrue(checkAlive(sunwukong), "孙悟空已死");

              }
              try {
                  Thread.sleep(1000);
              } catch (InterruptedException e) {
                  e.printStackTrace();
              }


          }

          if(sunwukong.getSpeed() < zhubajie.getSpeed()){

              if(StatusEnum.NORMAL.getCode().equals(zhubajie.getStatus())) {
                  Integer leftHealthForSun = sunwukong.getHealth() - (zhubajie.getAttack() - sunwukong.getDefence());
                  sunwukong.setHealth(leftHealthForSun);
                  log.info("孙悟空受到{}点伤害, 剩余生命值：{}", zhubajie.getAttack() - sunwukong.getDefence(), leftHealthForSun);
                  Assert.isTrue(checkAlive(sunwukong), "孙悟空已死");

              }
              try {
                  Thread.sleep(1000);
              } catch (InterruptedException e) {
                  e.printStackTrace();
              }

              if(StatusEnum.NORMAL.getCode().equals(sunwukong.getStatus())) {
                  Integer leftHealthForZhu = zhubajie.getHealth() - (sunwukong.getAttack() - zhubajie.getDefence());
                  zhubajie.setHealth(leftHealthForZhu);
                  log.info("猪八戒受到{}点伤害, 剩余生命值：{}", sunwukong.getAttack() - zhubajie.getDefence(), leftHealthForZhu);
                  Assert.isTrue(checkAlive(zhubajie), "猪八戒已死");

              }
              try {
                  Thread.sleep(1000);
              } catch (InterruptedException e) {
                  e.printStackTrace();
              }
          }

          log.info("第{}回合结束", i);
      }
    }

    private static Boolean checkAlive(Object o){

        if(o instanceof Sunwukong){
            Sunwukong sunwukong = (Sunwukong)o;
            if(sunwukong.getHealth() <= 0) {
                log.info("孙悟空阵亡");
                return false;
            }
        }

        if(o instanceof Zhubajie){
            Zhubajie zhubajie = (Zhubajie)o;
            if(zhubajie.getHealth() <= 0) {
                log.info("猪八戒阵亡");
                return false;
            }
        }

        return true;
    }
}
