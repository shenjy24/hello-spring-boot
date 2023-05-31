package com.jonas.webflux;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WebFluxApplication {
    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(WebFluxApplication.class);
        springApplication.run(args);
    }
}