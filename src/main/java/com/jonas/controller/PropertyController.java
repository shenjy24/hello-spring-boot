package com.jonas.controller;

import com.jonas.component.property.PropertyConfigurer;
import com.jonas.domain.User;
import com.jonas.service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
public class PropertyController {
    @Autowired
    private PropertyService propertyService;

    @PostMapping("getUser")
    public User getUser(@RequestBody String userId) {
        return propertyService.getUser();
    }

    @PostMapping("listUser")
    public List<User> listUser() {
        return propertyService.listUser();
    }

    @PostMapping("getProperty")
    public String getProperty(String key) {
        return PropertyConfigurer.getString(key);
    }
}
