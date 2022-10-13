package com.jonas.jpa.controller;

import com.jonas.jpa.repository.mysql.bean.entity.User;
import com.jonas.jpa.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author shenjy
 * @createTime 2022/10/13 21:00
 * @description UserController
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @GetMapping("/get")
    public User getUser(@RequestParam Long userId) {
        return userService.getUser(userId);
    }
}
