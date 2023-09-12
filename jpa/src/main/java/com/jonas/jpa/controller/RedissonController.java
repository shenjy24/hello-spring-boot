package com.jonas.jpa.controller;

import com.jonas.jpa.repository.redis.RedissonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/redisson")
@RequiredArgsConstructor
public class RedissonController {

    private final RedissonRepository redissonRepository;

    @RequestMapping("/set")
    public void set(String key, String value) {
        redissonRepository.set(key, value);
    }

    @RequestMapping("/test-lock")
    public void testLock() {
        redissonRepository.testLock();
    }
}
