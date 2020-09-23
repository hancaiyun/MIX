package com.nicehancy.mix.web.config;

import com.nicehancy.mix.common.utils.RedissionLockUtil;
import com.nicehancy.mix.web.config.properties.RedissonProperties;
import org.apache.commons.lang3.StringUtils;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.redisson.config.SingleServerConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * redission自动装配
 * <p>
 * <p/>
 *
 * @author hancaiyun
 * @since 2020/9/23 10:18
 **/
@Configuration
@ConditionalOnClass(Config.class)
@EnableConfigurationProperties(RedissonProperties.class)
public class RedissonAutoConfig {

    @Autowired
    private RedissonProperties redissionProperties;

    /**
     * 哨兵模式自动装配
     * @return
     */
//    @Bean
//    @ConditionalOnProperty(name="redisson.master-name")
//    RedissonClient redissonSentinel() {
//        Config config = new Config();
//        SentinelServersConfig serverConfig = config.useSentinelServers().addSentinelAddress(redssionProperties.getSentinelAddresses())
//                .setMasterName(redssionProperties.getMasterName())
//                .setTimeout(redssionProperties.getTimeout())
//                .setMasterConnectionPoolSize(redssionProperties.getMasterConnectionPoolSize())
//                .setSlaveConnectionPoolSize(redssionProperties.getSlaveConnectionPoolSize());
//
//        if(StringUtils.isNotBlank(redssionProperties.getPassword())) {
//            serverConfig.setPassword(redssionProperties.getPassword());
//        }
//        return Redisson.create(config);
//    }

    /**
     * 单机模式自动装配
     * @return  redission
     */
    @Bean
    @ConditionalOnProperty(name="redisson.address")
    RedissonClient redissonSingle() {
        Config config = new Config();
        SingleServerConfig serverConfig = config.useSingleServer()
                .setAddress(redissionProperties.getAddress())
                .setTimeout(redissionProperties.getTimeout())
                .setConnectionPoolSize(redissionProperties.getConnectionPoolSize())
                .setConnectionMinimumIdleSize(redissionProperties.getConnectionMinimumIdleSize());
        if(StringUtils.isNotBlank(redissionProperties.getPassword())) {
            serverConfig.setPassword(redissionProperties.getPassword());
        }

        return Redisson.create(config);
    }

    /**
     * 装配locker类，并将实例注入到RedissionLockUtil中
     * @return  util
     */
    @Bean
    RedissionLockUtil redissionLockUtil(RedissonClient redissonClient) {
        RedissionLockUtil redissionLockUtil = new RedissionLockUtil();
        redissionLockUtil.setRedissonClient(redissonClient);
        return redissionLockUtil;
    }
}
