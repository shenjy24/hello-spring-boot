package com.jonas.base.component.runner;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * @author shenjy
 * @createTime 2022/10/8 15:08
 * @description SpringBoot项目启动时，会自动执行ApplicationRunner中的run方法
 */
@Component
public class CustomApplicationRunner implements ApplicationRunner {
    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println(args);
    }
}
