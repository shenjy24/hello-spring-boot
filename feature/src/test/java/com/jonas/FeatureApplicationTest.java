package com.jonas;

import com.jonas.feature.component.property.PropertyConfigurer;

import org.junit.Test;
import org.junit.runner.RunWith;
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

    @Test
    public void testProperty() {
        String port = PropertyConfigurer.getString("server.port");
        System.out.println(port);
    }
}
