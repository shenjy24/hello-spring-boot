package com.jonas.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author shenjy
 * @createTime 2022/2/25 14:45
 * @description WebConfig
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private CrossOriginInterceptor crossOriginInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(crossOriginInterceptor);
    }
}
