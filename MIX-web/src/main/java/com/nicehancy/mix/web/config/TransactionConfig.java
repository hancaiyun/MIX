package com.nicehancy.mix.web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.MongoTransactionManager;

/**
 * MongoDB事务配置类
 * <p>
 * <p/>
 *
 * @author hancaiyun
 * @since 2020/5/18 14:10
 **/
@Configuration
public class TransactionConfig {
    @Bean
    MongoTransactionManager transactionManager(MongoDbFactory factory){
        return new MongoTransactionManager(factory);
    }
}
