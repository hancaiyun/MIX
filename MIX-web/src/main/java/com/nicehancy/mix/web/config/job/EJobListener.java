package com.nicehancy.mix.web.config.job;

import com.dangdang.ddframe.job.executor.ShardingContexts;
import com.dangdang.ddframe.job.lite.api.listener.ElasticJobListener;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;

/**
 * 定时任务监听器
 * <p>
 * <p/>
 *
 * @author hancaiyun
 * @since 2020/8/10 18:37
 **/
@Slf4j
public class EJobListener implements ElasticJobListener {

    @Override
    public void beforeJobExecuted(ShardingContexts shardingContexts) {
        log.info("===>{} 定时任务执行开始 {} <===",shardingContexts.getJobName(), new Date());
    }

    @Override
    public void afterJobExecuted(ShardingContexts shardingContexts) {
        log.info("===>{} 定时任务执行结束:{} <===",shardingContexts.getJobName(), new Date());
    }
}
