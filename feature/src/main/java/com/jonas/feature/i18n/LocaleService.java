package com.jonas.feature.i18n;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
public class LocaleService {


    @Autowired
    private LocaleHandler localeHandler;

    public String getUserName(String key) {
        return localeHandler.getMessageByServer(key);
    }

    public String getUserName(String key, HttpServletRequest request) {
        return localeHandler.getMessageByRequest(key, request);
    }
}
