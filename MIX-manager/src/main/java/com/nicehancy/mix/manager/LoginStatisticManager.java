package com.nicehancy.mix.manager;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * 登陆统计记录表manager
 * <p>
 * <p/>
 *
 * @author hancaiyun
 * @since 2020/8/11 10:10
 **/
@Slf4j
@Component
public class LoginStatisticManager {

    /**
     * 登陆数据统计，生成日、月报表
     */
    public void loginStatistic() {

        //1、查询登陆记录表
        log.info("dasdadsadsadadsadsadsad");
        //2、按日、月计算报表数据

        //3、插入登陆统计报表

    }
}
