package com.jonas.feature.event;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class CustomApplicationEventListener {

    @EventListener
    public void onApplicationEvent(CustomApplicationEvent event) {
        System.out.println("接收到事件：" + event.getClass());
    }
}
