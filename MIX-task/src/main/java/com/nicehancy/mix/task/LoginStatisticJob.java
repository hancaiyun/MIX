package com.nicehancy.mix.task;

import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;
import com.nicehancy.mix.common.constant.CommonConstant;
import com.nicehancy.mix.manager.LoginStatisticManager;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.UUID;

/**
 * 登陆数据统计定时任务
 * <p>
 * <p/>
 *
 * @author hancaiyun
 * @since 2020/8/11 9:33
 **/
@Slf4j
@Component
public class LoginStatisticJob implements SimpleJob {

    @Autowired
    private LoginStatisticManager loginStatisticManager;

    @Override
    public void execute(ShardingContext shardingContext) {
        //登陆数据统计定时任务业务处理
        MDC.put(CommonConstant.TRACE_LOG_ID, UUID.randomUUID().toString());
        try{
            loginStatisticManager.loginStatistic();
        }catch (Exception e){
            log.error("登陆数据统计定时任务处理失败， 失败原因:{}", e.getMessage());
        }finally {
            MDC.clear();
        }
    }
}
