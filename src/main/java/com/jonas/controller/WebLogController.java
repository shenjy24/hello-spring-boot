package com.jonas.controller;

import com.jonas.domain.LogRequest;
import com.jonas.util.WebLogParser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
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
