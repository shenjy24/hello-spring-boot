package com.jonas.feature.event;

import org.springframework.context.ApplicationEvent;

/**
 * 自定义事件
 */
public class CustomApplicationEvent extends ApplicationEvent {

    public CustomApplicationEvent(Object source) {
        super(source);
        System.err.println("发布事件：source = " + source);
    }
}
