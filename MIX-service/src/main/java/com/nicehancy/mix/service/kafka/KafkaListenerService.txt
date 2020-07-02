package com.nicehancy.mix.service.kafka;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * kafka统计监听
 * <p>
 * <p/>
 *
 * @author hancaiyun
 * @since 2020/5/7 14:04
 **/
@Slf4j
@Component
public class KafkaListenerService {

    @KafkaListener(topics = {"${spring.kafka.consumer.topic-order-info}"})
    public void listen(ConsumerRecord<String, String> data) {
        log.info("data value：{}", data.value());
        //转成对象DTO

    }
}
