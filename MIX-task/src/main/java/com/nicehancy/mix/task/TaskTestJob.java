package com.nicehancy.mix.task;

import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * 定时任务
 * <p>
 * <p/>
 *
 * @author hancaiyun
 * @since 2020/8/10 18:47
 **/
@Slf4j
@Component
public class TaskTestJob implements SimpleJob {

    @Override
    public void execute(ShardingContext shardingContext) {
        log.info("正在执行~~~~~~~~~~~~~~~~~~");
    }
}
