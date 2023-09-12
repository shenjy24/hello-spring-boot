package com.jonas.jpa.repository.redis;

import lombok.RequiredArgsConstructor;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RedissonRepository {

    private final RedissonClient redisson;

    public void set(String key, String value) {
        redisson.getBucket(key).set(value);
    }

    public void testLock() {
        // 1.获取锁，只要锁的名字一样，获取到的锁就是同一把锁。
        RLock lock = redisson.getLock("redisson-lock");
        // 2.加锁
        lock.lock();
        try {
            System.out.println("加锁成功，执行后续代码。线程 ID：" + Thread.currentThread().getId());
            Thread.sleep(1000);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 3.解锁
            lock.unlock();
            System.out.println("Finally，释放锁成功。线程 ID：" + Thread.currentThread().getId());
        }
    }
}
