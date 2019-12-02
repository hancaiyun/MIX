package com.nicehancy.MIX;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan
public class MixApplication {

	public static void main(String[] args) {
		SpringApplication.run(MixApplication.class, args);
	}

}