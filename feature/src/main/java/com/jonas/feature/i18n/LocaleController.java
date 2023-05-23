package com.jonas.feature.i18n;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/locale")
@RequiredArgsConstructor
public class LocaleController {

    private final LocaleService localeService;

    @RequestMapping("/get")
    public String getUserName(String key) {
        return localeService.getUserName(key);
    }

    @RequestMapping("/request/get")
    public String getUserName(String key, HttpServletRequest request) {
        return localeService.getUserName(key, request);
    }
}
