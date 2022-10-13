package com.jonas.jpa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author shenjy
 * @createTime 2022/10/13 19:28
 * @description JpaApplication
 */
@SpringBootApplication
public class JpaApplication {
    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(JpaApplication.class);
        springApplication.run(args);
    }
}
