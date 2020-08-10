package com.nicehancy.mix.web.config.job;

import com.dangdang.ddframe.job.api.simple.SimpleJob;
import com.dangdang.ddframe.job.config.JobCoreConfiguration;
import com.dangdang.ddframe.job.config.simple.SimpleJobConfiguration;
import com.dangdang.ddframe.job.lite.api.listener.ElasticJobListener;
import com.dangdang.ddframe.job.lite.config.LiteJobConfiguration;
import com.dangdang.ddframe.job.lite.spring.api.SpringJobScheduler;
import com.dangdang.ddframe.job.reg.zookeeper.ZookeeperRegistryCenter;
import com.nicehancy.mix.task.TaskTestJob;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 定时任务bean配置
 * <p>
 * <p/>
 *
 * @author hancaiyun
 * @since 2020/8/10 18:44
 **/
@Configuration
public class ElasticJobConfig {

    @Autowired
    private ZookeeperRegistryCenter regCenter;

    /**
     * 配置任务监听器
     *
     * @return
     */
    @Bean
    public ElasticJobListener elasticJobListener() {
        return new EJobListener();
    }

    /**
     * 配置任务详细信息
     *
     * @param jobClass                  任务class
     * @param cron                      corn表达式
     * @param shardingTotalCount        分片值
     * @param shardingItemParameters    分片参数
     * @return                          config
     */
    private LiteJobConfiguration getLiteJobConfiguration(final Class<? extends SimpleJob> jobClass,
                                                         final String cron,
                                                         final int shardingTotalCount,
                                                         final String shardingItemParameters) {
        return LiteJobConfiguration.newBuilder(new SimpleJobConfiguration(
                JobCoreConfiguration.newBuilder(jobClass.getName(), cron, shardingTotalCount)
                        .shardingItemParameters(shardingItemParameters).build()
                , jobClass.getCanonicalName())
        ).overwrite(true).monitorExecution(true).build();
    }

    /**
     * 商户自主入驻申请员工认领逾期定时任务
     * @param job                      job
     * @param cron                     cron表达式
     * @param shardingTotalCount       分片数
     * @param shardingItemParameters   分片参数
     * @return
     */
    @Bean(initMethod = "init")
    public SpringJobScheduler taskQuasiMerchantAssignOverdueJobScheduler(final TaskTestJob job,
                                                                         @Value("${testJob.cron}")final String cron,
                                                                         @Value("${testJob.shardingTotalCount}") final int shardingTotalCount,
                                                                         @Value("${testJob.shardingItemParameters}") final String shardingItemParameters) {
        EJobListener elasticJobListener = new EJobListener();
        return new SpringJobScheduler(job, regCenter,
                getLiteJobConfiguration(job.getClass(), cron, shardingTotalCount, shardingItemParameters),
                elasticJobListener);
    }
}
