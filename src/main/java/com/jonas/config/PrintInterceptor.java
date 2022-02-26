package com.jonas.config;

import com.jonas.domain.User;
import com.jonas.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author shenjy
 * @createTime 2022/2/25 14:40
 * @description 打印出请求日志
 */
@Slf4j
@Component
public class PrintInterceptor implements HandlerInterceptor {

    @Autowired
    private UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        User user = userService.getUser();
        log.info(user.toString());
        return HandlerInterceptor.super.preHandle(request, response, handler);
    }
}
