package com.jonas.feature.filter;

import org.springframework.stereotype.Component;

import javax.servlet.FilterConfig;
import javax.servlet.*;
import java.io.IOException;

@Component
public class LogCostFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("LogCostFilter: init");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        long start = System.currentTimeMillis();
        System.out.println("LogCostFilter: start=" + start);
        chain.doFilter(request, response);
        long end = System.currentTimeMillis();
        System.out.println("LogCostFilter: end=" + end);
        System.out.println("LogCostFilter: cost=" + (end - start));
    }

    @Override
    public void destroy() {
        System.out.println("LogCostFilter: destroy");
    }
}
