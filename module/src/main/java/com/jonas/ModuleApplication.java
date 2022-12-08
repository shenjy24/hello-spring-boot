package com.jonas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author shenjy
 * @createTime 2022/10/13 19:28
 * @description ModuleApplication
 */
@SpringBootApplication
public class ModuleApplication {
    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(ModuleApplication.class);
        springApplication.run(args);
    }
}
