package com.jonas;

import com.jonas.feature.context.SpringContextListener;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@SpringBootApplication
public class FeatureApplication {
    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(FeatureApplication.class);
        springApplication.addListeners(new SpringContextListener());
        springApplication.run(args);
    }
}
