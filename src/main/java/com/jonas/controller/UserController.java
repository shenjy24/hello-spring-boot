package com.jonas.controller;

import com.jonas.component.PropertyConfigurer;
import com.jonas.domain.User;
import com.jonas.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("getUser")
    public User getUser() {
        return userService.getUser();
    }

    @PostMapping("getUserByName")
    public User getUser(String name) {
        return userService.getUser(name);
    }

    @PostMapping("listUser")
    public List<User> listUser() {
        return userService.listUser();
    }

    @PostMapping("getProperty")
    public String getProperty(String key) {
        return PropertyConfigurer.getString(key);
    }
}
