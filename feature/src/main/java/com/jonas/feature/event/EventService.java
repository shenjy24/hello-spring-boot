package com.jonas.feature.event;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class EventService {
    @Resource
    private ApplicationContext context;

    /**
     * 发布事件
     *
     * @param source 事件源
     */
    public void publishEvent(String source) {
        context.publishEvent(new CustomApplicationEvent(source));
    }
}
