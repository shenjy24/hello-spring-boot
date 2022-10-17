package com.jonas.base.controller;

import com.jonas.base.service.AsyncService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

/**
 * @author shenjy
 * @createTime 2022/3/1 15:05
 * @description ContextController
 */
@Slf4j
@RestController
@RequestMapping("/async/")
public class AsyncController {

    @Autowired
    private AsyncService asyncService;

    @PostMapping("doTask")
    public void doTask() {
        log.info("[{}] start time : {}", Thread.currentThread().getName(), System.currentTimeMillis());
        asyncService.doTask();
        log.info("[{}] end time : {}", Thread.currentThread().getName(), System.currentTimeMillis());
    }

    @PostMapping("doAsyncTaskWithThis")
    public void doAsyncTaskWithThis() {
        log.info("[{}] start time : {}", Thread.currentThread().getName(), System.currentTimeMillis());
        asyncService.doAsyncTaskWithThis();
        log.info("[{}] end time : {}", Thread.currentThread().getName(), System.currentTimeMillis());
    }

    @PostMapping("doAsyncTaskWithContext")
    public void doAsyncTaskWithContext() {
        log.info("[{}] start time : {}", Thread.currentThread().getName(), System.currentTimeMillis());
        asyncService.doAsyncTaskWithContext();
        log.info("[{}] end time : {}", Thread.currentThread().getName(), System.currentTimeMillis());
    }

    @PostMapping("doAsyncTask")
    public void doAsyncTask() {
        log.info("[{}] start time : {}", Thread.currentThread().getName(), System.currentTimeMillis());
        asyncService.doAsyncTask();
        log.info("[{}] end time : {}", Thread.currentThread().getName(), System.currentTimeMillis());
    }

    @PostMapping("doOtherAsyncTask")
    public void doOtherAsyncTask() {
        log.info("[{}] start time : {}", Thread.currentThread().getName(), System.currentTimeMillis());
        asyncService.doOtherAsyncTask();
        log.info("[{}] end time : {}", Thread.currentThread().getName(), System.currentTimeMillis());
    }

    @PostMapping("doMultiAsyncTask")
    public void doMultiAsyncTask() {
        log.info("[{}] start time : {}", Thread.currentThread().getName(), System.currentTimeMillis());
        CompletableFuture<String> future1 = asyncService.doAsyncTaskOne();
        CompletableFuture<String> future2 = asyncService.doAsyncTaskTwo();
        // 两个异步任务结束后再往下执行
        CompletableFuture.allOf(future1, future2).join();
        log.info("[{}] end time : {}", Thread.currentThread().getName(), System.currentTimeMillis());
    }
}
