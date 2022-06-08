package com.jonas.controller;

import com.jonas.component.PropertyConfigurer;
import com.jonas.domain.User;
import com.jonas.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotBlank;

@Validated
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("getUser")
    public User getUser(@RequestBody String userId) {
        return userService.getUser();
    }

    @PostMapping("getProperty")
    public String getProperty(String key) {
        return PropertyConfigurer.getString(key);
    }
}
