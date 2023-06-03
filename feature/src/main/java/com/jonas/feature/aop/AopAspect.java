package com.jonas.feature.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * @author shenjy
 * @createTime 2023/6/3 17:47
 * @description
 */
@Aspect
@Component
public class AopAspect {
    // 拦截SampleAdder中的public方法
    @Pointcut("execution(public * com.jonas.feature.aop.AopService.*(..))")
    public void aspect() {
    }

    @Before("aspect()")
    public void beforeInitUser() {
        System.out.println("beforeInit");
    }

    @After("aspect()")
    public void afterInitUser() {
        System.out.println("afterInit");
    }

    @Around("aspect()")
    public Object aroundInitUser(ProceedingJoinPoint joinPoint) {
        System.out.println("beforeAroundInit");
        try {
            return joinPoint.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        System.out.println("afterAroundInit");
        return null;
    }
}
