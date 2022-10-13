package com.jonas.base.component;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingClass;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * GlobalExceptionHandler
 *
 * @author shenjy
 * @version 1.0
 * @date 2022-07-16
 */
@Slf4j
@ControllerAdvice
@ConditionalOnMissingClass
public class GlobalExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public void handle(MethodArgumentNotValidException e) {
        // 输出校验文本提示
        log.info(e.getBindingResult().getAllErrors().get(0).getDefaultMessage());
    }
}
