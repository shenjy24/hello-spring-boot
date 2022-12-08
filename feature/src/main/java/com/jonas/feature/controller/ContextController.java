package com.jonas.feature.controller;

import com.jonas.feature.component.context.SpringContext;
import com.jonas.feature.bean.User;
import com.jonas.feature.service.PropertyService;
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

    @PostMapping("getUser")
    public User getUser() {
        PropertyService propertyService = SpringContext.getBean(PropertyService.class);
        return propertyService.getUser();
    }
}
