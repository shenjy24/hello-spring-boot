package com.jonas.jpa.controller;

import com.jonas.jpa.repository.mysql.bean.req.UserCreateReq;
import com.jonas.jpa.repository.mysql.bean.view.UserView;
import com.jonas.jpa.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @PostMapping("/get")
    public UserView getUser(@RequestParam Long userId) {
        return userService.getUser(userId);
    }

    @PostMapping("/listUserByAgeLessThan")
    public List<UserView> listUserByAgeLessThan(@RequestParam Integer age) {
        return userService.listUserByAgeLessThan(age);
    }

    @PostMapping("/create")
    public UserView createUser(@RequestBody UserCreateReq req) {
        return userService.createUser(req);
    }
}
