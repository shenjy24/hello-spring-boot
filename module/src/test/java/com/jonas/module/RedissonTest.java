package com.jonas.module;

import com.jonas.ModuleApplication;
import com.jonas.module.redisson.RedissonService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@SpringBootTest
@RunWith(SpringRunner.class)
public class RedissonTest {

    @Resource
    private RedissonService redissonService;

    @Test
    public void testSet() {
        redissonService.set("hello", "redisson");
    }

    @Test
    public void testLock() {
        for (int i = 0; i < 5; i++) {
            redissonService.testLock();
        }
    }
}
