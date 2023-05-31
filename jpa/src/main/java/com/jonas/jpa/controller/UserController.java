package com.jonas.jpa.controller;

import com.jonas.bean.BizException;
import com.jonas.bean.SystemCode;
import com.jonas.jpa.repository.mysql.bean.req.UserCreateReq;
import com.jonas.jpa.repository.mysql.bean.view.UserView;
import com.jonas.jpa.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author shenjy
 * @createTime 2022/10/13 21:00
 * @description UserController
 */
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @PostMapping("/get")
    public UserView getUser(@RequestParam Long userId) {
        return userService.getUser(userId);
    }

    @PostMapping("/get/valid")
    public UserView validUser(@NotNull(message = "用户ID不为空") Long userId) {
        return userService.getUser(userId);
    }

    @PostMapping("/get/throw")
    public UserView throwUser(Long userId) {
        throw new BizException(SystemCode.PARAM_ERROR);
    }

    @PostMapping("/listUserByAgeLessThan")
    public List<UserView> listUserByAgeLessThan(@RequestParam Integer age) {
        return userService.listUserByAgeLessThan(age);
    }

    @PostMapping("/create")
    public UserView createUser(@Valid @RequestBody UserCreateReq req,
                               @RequestHeader("Accept-Language") String language) {
        if (!language.isBlank()) {
            req.setLanguage(language);
        }
        return userService.createUser(req);
    }
}
