package com.jonas.feature.async;

import com.jonas.feature.context.SpringContext;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

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
        log.info("[{}] task1 cost {} ms", Thread.currentThread().getName(), end - start);
    }

    /**
     * 同步操作调用异步操作，同一个类中异步不生效
     */
    @SneakyThrows
    public void doAsyncTaskWithThis() {
        long start = System.currentTimeMillis();
        Thread.sleep(2000);
        this.doAsyncTask();
        long end = System.currentTimeMillis();
        log.info("[{}] asyncTaskWithThis cost {} ms", Thread.currentThread().getName(), end - start);
    }

    /**
     * 同步操作调用异步操作，同一个类中调用需要使用从容器中获取bean，异步才生效
     */
    @SneakyThrows
    public void doAsyncTaskWithContext() {
        long start = System.currentTimeMillis();
        Thread.sleep(2000);
        AsyncService asyncService = SpringContext.getBean(AsyncService.class);
        asyncService.doAsyncTask();
        long end = System.currentTimeMillis();
        log.info("[{}] asyncTaskWithContext cost {} ms", Thread.currentThread().getName(), end - start);
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
        log.info("[{}] asyncTask cost {} ms", Thread.currentThread().getName(), end - start);
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
        log.info("[{}] otherAsyncTask cost {} ms", Thread.currentThread().getName(), end - start);
    }

    @Async
    @SneakyThrows
    public CompletableFuture<String> doAsyncTaskOne() {
        long start = System.currentTimeMillis();
        Thread.sleep(2000);
        long end = System.currentTimeMillis();
        log.info("[{}] asyncTaskOne cost {} ms", Thread.currentThread().getName(), end - start);
        return CompletableFuture.completedFuture("异步任务1完成");
    }

    @Async
    @SneakyThrows
    public CompletableFuture<String> doAsyncTaskTwo() {
        long start = System.currentTimeMillis();
        Thread.sleep(2000);
        long end = System.currentTimeMillis();
        log.info("[{}] asyncTaskTwo cost {} ms", Thread.currentThread().getName(), end - start);
        return CompletableFuture.completedFuture("异步任务2完成");
    }
}
