package com.nicehancy.mix.cardgame.card;

import com.nicehancy.mix.base.BaseSpringTest;
import com.nicehancy.mix.cardgame.card.cards.Sunwukong;
import com.nicehancy.mix.cardgame.card.cards.Zhubajie;
import com.nicehancy.mix.cardgame.card.way.Fight;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * 回合制卡牌
 * <p>
 * <p/>
 *
 * @author hancaiyun
 * @since 2020/8/21 13:45
 **/
@Slf4j
public class FightMain extends BaseSpringTest {

    @Test
    public void fight(){

        Sunwukong sunwukong = new Sunwukong();
        Zhubajie zhubajie = new Zhubajie();

        Fight.start(sunwukong, zhubajie);
    }
}
