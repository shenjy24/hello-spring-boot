package com.jonas.module.redisson;

import lombok.RequiredArgsConstructor;
import org.redisson.api.RedissonClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/redisson")
@RequiredArgsConstructor
public class RedissonController {

    private final RedissonService redissonService;

    @RequestMapping("/set")
    public void set(String key, String value) {
        redissonService.set(key, value);
    }

    @RequestMapping("/test-lock")
    public void testLock() {
        redissonService.testLock();
    }
}
