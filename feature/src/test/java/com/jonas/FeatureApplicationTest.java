package com.jonas;

import com.jonas.feature.aop.AopService;
import com.jonas.feature.property.PropertyConfigurer;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author shenjy
 * @createTime 2022/2/28 14:47
 * @description ApplicationTest
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class FeatureApplicationTest {

    @Autowired
    private AopService adder;

    @Test
    public void testProperty() {
        String port = PropertyConfigurer.getString("server.port");
        System.out.println(port);
    }

    @Test
    public void testAspect() {
        int res = adder.add(1, 2);
        System.out.println(res);
    }
}
