package com.bndrs.voice;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootApplication(scanBasePackages = "com.bndrs.voice")
@MapperScan("com.bndrs.voice.dao")
@SpringBootTest
public class ServiceApplication4UT {
    public static void main(String[] args) {
        SpringApplication.run(ServiceApplication4UT.class, args);
    }
}
