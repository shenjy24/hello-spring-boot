package com.jonas.component;

import org.springframework.context.ApplicationContext;

/**
 * @author shenjy
 * @createTime 2022/3/1 15:02
 * @description SpringContext
 */
public class SpringContext {

    private static ApplicationContext applicationContext;

    public static void setApplicationContext(ApplicationContext context) {
        applicationContext = context;
    }

    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    public static Object getBean(String name) {
        return applicationContext.getBean(name);
    }

    public static <T> T getBean(Class<T> requiredType) {
        return applicationContext.getBean(requiredType);
    }
}
