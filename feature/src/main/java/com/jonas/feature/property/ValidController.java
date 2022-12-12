package com.jonas.feature.property;

import com.jonas.common.bean.User;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

/**
 * ValidController
 *
 * @author shenjy
 * @version 1.0
 * @date 2022-07-16
 */
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/valid/")
public class ValidController {

    private final PropertyService propertyService;

    @PostMapping("getUserByName")
    public User getUserByName(@NotBlank(message = "name不能为空") String name) {
        return propertyService.getUser(name);
    }

    @PostMapping("saveUser")
    public void saveUser(@RequestBody @Valid User user) {
        System.out.println(user);
    }
}
