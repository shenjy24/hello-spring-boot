package com.jonas.service;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * @author shenjy
 * @createTime 2022/7/15 13:44
 * @description AsyncService
 */
@Slf4j
@Service
public class AsyncService {

    /**
     * 同步操作
     */
    @SneakyThrows
    public void doTask() {
        long start = System.currentTimeMillis();
        Thread.sleep(2000);
        long end = System.currentTimeMillis();
        log.info("[{}] task cost {} ms", Thread.currentThread().getName(), end - start);
    }

    /**
     * 使用defaultExecutor线程池
     */
    @Async
    @SneakyThrows
    public void doAsyncTask() {
        long start = System.currentTimeMillis();
        Thread.sleep(2000);
        long end = System.currentTimeMillis();
        log.info("[{}] task cost {} ms", Thread.currentThread().getName(), end - start);
    }

    /**
     * 使用otherExecutor线程池
     */
    @SneakyThrows
    @Async("otherExecutor")
    public void doOtherAsyncTask() {
        long start = System.currentTimeMillis();
        Thread.sleep(2000);
        long end = System.currentTimeMillis();
        log.info("[{}] task cost {} ms", Thread.currentThread().getName(), end - start);
    }
}
