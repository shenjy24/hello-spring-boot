package com.jonas.feature.i18n;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.support.RequestContextUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;

@Component
public class LocaleHandler {

    @Autowired
    @Qualifier("reloadableResourceBundleMessageSource")
    private MessageSource messageSource;

    //根据 Request 请求获取资源信息
    public String getMessageByRequest(String key, HttpServletRequest request) {
        Locale locale = RequestContextUtils.getLocale(request);
        String message = messageSource.getMessage(key, null, locale);
        return message;
    }

    //根据部署服务器获取资源信息
    public String getMessageByServer(String key) {
        // 可以拿到本次请求header里的Accept-Language对应的语言环境
        Locale locale = LocaleContextHolder.getLocale();
        String message = messageSource.getMessage(key, null, locale);
        return message;
    }
}
