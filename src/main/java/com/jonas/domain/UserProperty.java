package com.jonas.domain;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Data
@Component
@PropertySource("user.properties")
@ConfigurationProperties("user")
public class UserProperty {

    private String userName;
    private int userAge;

}
