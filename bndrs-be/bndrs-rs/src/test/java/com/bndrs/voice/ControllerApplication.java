package com.bndrs.voice;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
@SpringBootApplication(scanBasePackages = "com.bndrs.voice")
@MapperScan("com.bndrs.voice.dao")
@SpringBootTest
public class ControllerApplication {

    /**
     * The entry point of the application.
     *
     * @param args the input arguments
     * @sinctraining-device0.1.0
     */
    public static void main(String[] args) {
        SpringApplication.run(ControllerApplication.class, args);
    }

}
