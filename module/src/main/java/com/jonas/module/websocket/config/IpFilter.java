package com.jonas.module.websocket.config;

import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

import static com.jonas.module.websocket.config.WebSocketConfigurator.IP_ATTR;

@Component
public class IpFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        request.getSession().setAttribute(IP_ATTR,  request.getRemoteAddr() + ":" + request.getRemotePort());
        filterChain.doFilter(request, servletResponse);
    }
}
