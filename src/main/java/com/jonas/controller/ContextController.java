package com.jonas.controller;

import com.jonas.component.context.SpringContext;
import com.jonas.domain.User;
import com.jonas.service.PropertyService;
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
