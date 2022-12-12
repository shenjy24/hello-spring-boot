package com.jonas.feature.context;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author shenjy
 * @createTime 2022/3/1 15:05
 * @description ContextController
 */
@RestController
@RequestMapping("/context/")
public class ContextController {

    @PostMapping("getActiveProfile")
    public String getActiveProfile() {
        ContextService propertyService = SpringContext.getBean(ContextService.class);
        return propertyService.getActiveProfile();
    }
}
