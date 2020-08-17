package com.nicehancy.mix.web.config.job;

import com.dangdang.ddframe.job.executor.ShardingContexts;
import com.dangdang.ddframe.job.lite.api.listener.ElasticJobListener;
import lombok.extern.slf4j.Slf4j;

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

    Long start;
    Long end;

    @Override
    public void beforeJobExecuted(ShardingContexts shardingContexts) {
        start = System.currentTimeMillis();
        log.info("===>{} job start",shardingContexts.getJobName());
    }

    @Override
    public void afterJobExecuted(ShardingContexts shardingContexts) {
        end = System.currentTimeMillis();
        log.info("===>{} job end, use time:[{}]ms",shardingContexts.getJobName(), end - start);
    }
}
