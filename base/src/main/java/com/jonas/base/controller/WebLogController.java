package com.jonas.base.controller;

import com.jonas.base.repository.domain.LogRequest;
import com.jonas.base.util.WebLogParser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author shenjy
 * @createTime 2022/7/22 13:47
 * @description 前端日志上报
 */
@Slf4j
@RestController
@RequestMapping("/web/log")
public class WebLogController {
    /**
     * 前端日志上报接口
     */
    @RequestMapping("/report")
    public void report(@RequestBody LogRequest logRequest) {
        log.info(logRequest.toString());
        List<String> logs = WebLogParser.parse(logRequest.getLogArray());
        logs.forEach(System.out::println);
    }
}
