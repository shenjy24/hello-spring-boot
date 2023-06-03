package com.jonas.feature.aop;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
@RequestMapping("/aop/")
@RequiredArgsConstructor
public class AopController {

    private final AopService adder;

    @PostMapping("add")
    public int add(int a, int b) {
        return adder.add(a, b);
    }
}
