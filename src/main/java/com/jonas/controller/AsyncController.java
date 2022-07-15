package com.jonas.controller;

import com.jonas.service.AsyncService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
