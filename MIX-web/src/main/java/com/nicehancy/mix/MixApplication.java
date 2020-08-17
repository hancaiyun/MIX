package com.nicehancy.mix;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author CY
 */
@Slf4j
@SpringBootApplication
@ServletComponentScan
//@EnableKafka
@EnableTransactionManagement
@EnableDubbo(scanBasePackages = "com.nicehancy.MIX.service")
public class MixApplication {

	public static void main(String[] args) {
		SpringApplication.run(MixApplication.class, args);
		log.info("MIX Service is started...");
	}
}