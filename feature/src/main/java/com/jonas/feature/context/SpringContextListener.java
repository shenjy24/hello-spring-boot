package com.jonas.feature.context;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ApplicationContextEvent;
import org.springframework.context.event.ContextRefreshedEvent;

/**
 * @author shenjy
 * @createTime 2022/3/1 15:03
 * @description SpringContextListener
 */
public class SpringContextListener implements ApplicationListener<ApplicationContextEvent> {
    @Override
    public void onApplicationEvent(ApplicationContextEvent event) {
        if (event instanceof ContextRefreshedEvent) {
            SpringContext.setApplicationContext(event.getApplicationContext());
        }
    }
}
