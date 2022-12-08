package com.jonas.module.weblog;

import com.jonas.module.weblog.util.WebLogParser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author shenjy
 * @createTime 2022/7/22 13:47
 * @description 前端日志上报 https://github.com/Meituan-Dianping/Logan
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
