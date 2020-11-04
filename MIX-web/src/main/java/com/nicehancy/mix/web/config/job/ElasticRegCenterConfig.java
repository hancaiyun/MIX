package com.nicehancy.mix.web.config.job;

import com.dangdang.ddframe.job.reg.zookeeper.ZookeeperConfiguration;
import com.dangdang.ddframe.job.reg.zookeeper.ZookeeperRegistryCenter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 注册中心配置
 * <p>
 * <p/>
 *
 * @author hancaiyun
 * @since 2020/8/10 18:43
 **/
@Configuration
public class ElasticRegCenterConfig {

    /**
     * 配置zookeeper
     * @param serverList    server列表
     * @param namespace     命名空间
     * @return              zk
     */
    @Bean(initMethod = "init")
    public ZookeeperRegistryCenter regCenter(@Value("${regCenter.serverList}") final String serverList,
                                             @Value("${regCenter.namespace}") final String namespace) {
        return new ZookeeperRegistryCenter(new ZookeeperConfiguration(serverList, namespace));
    }
}
