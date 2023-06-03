package com.jonas.feature.aop;

import org.springframework.stereotype.Component;

/**
 * @author shenjy
 * @createTime 2023/6/3 17:46
 * @description
 */
@Component
public class AopService {
    public int add(int a, int b) {
        return a + b;
    }
}
