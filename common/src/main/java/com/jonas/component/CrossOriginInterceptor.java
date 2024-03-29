package com.jonas.component;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author shenjy
 * @createTime 2022/7/22 14:37
 * @description 跨域拦截器
 */
@Slf4j
@Component
public class CrossOriginInterceptor implements HandlerInterceptor {

    @Value("${server.domain:*}")
    private String serverDomain;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("server domain : " + serverDomain);
        response.setHeader("Access-Control-Allow-Origin", serverDomain);
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Allow-Headers", "content-type");
        return true;
    }
}
