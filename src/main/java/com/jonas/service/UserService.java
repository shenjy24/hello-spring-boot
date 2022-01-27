package com.jonas.service;

import com.jonas.domain.User;
import com.jonas.domain.UserProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@PropertySource("user.properties")
//@ConditionalOnProperty(prefix = "user", name = "enable", havingValue = "true")
public class UserService {

    @Value("${user.userName}")
    private String userName;
    @Value("${user.userAge:40}")
    private int userAge;
    @Value("#{systemProperties['user.userHobbies'] ?: T(java.util.Arrays).asList('swim')}")
    private List<String> userHobbies;

    @Autowired
    private UserProperty userProperty;

    public User getUser() {
        return new User(userProperty.getUserName(), userProperty.getUserAge(), new ArrayList<>());
    }

    public User getUserInfo() {
        return new User(userName, userAge, userHobbies);
    }
}
