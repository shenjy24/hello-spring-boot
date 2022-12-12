package com.jonas.feature.property;

import com.jonas.common.bean.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/property/")
public class PropertyController {

    private final PropertyService propertyService;

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
