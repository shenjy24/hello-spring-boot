package com.jonas.service;

import com.jonas.domain.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
@PropertySource("user.properties")
public class UserService {

    @Value("${user.userName}")
    private String userName;
    @Value("${user.userAge:40}")
    private int userAge;
    @Value("#{systemProperties['user.userHobbies'] ?: T(java.util.Arrays).asList('swim')}")
    private List<String> userHobbies;

    public User getUser() {
        return new User(userName, userAge, userHobbies);
    }
}
