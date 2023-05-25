package com.jonas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author shenjy
 * @createTime 2022/10/13 19:28
 * @description JpaApplication
 */
@SpringBootApplication
@EnableTransactionManagement
public class JpaApplication {
    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(JpaApplication.class);
        springApplication.run(args);
    }
}
