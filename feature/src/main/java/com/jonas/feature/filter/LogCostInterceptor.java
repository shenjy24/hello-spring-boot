package com.jonas.feature.filter;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class LogCostInterceptor implements HandlerInterceptor {
    private final ThreadLocal<Long> start = new ThreadLocal<>();

    /**
     * preHandle是请求执行前执行
     */
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        start.set(System.currentTimeMillis());
        System.out.println("LogCostInterceptor: start=" + start.get());
        return true;
    }

    /**
     * postHandler是请求结束执行的，但只有preHandle方法返回true的时候才会执行
     */
    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
        System.out.println("LogCostInterceptor: cost=" + (System.currentTimeMillis() - start.get()));
    }

    /**
     * afterCompletion是视图渲染完成后才执行，同样需要preHandle返回true，该方法通常用于清理资源等工作
     */
    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
        start.remove();
        System.out.println("LogCostInterceptor: after");
    }
}
