package com.jonas.feature.property;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Data
@Component
@PropertySource("user.properties")
@ConfigurationProperties("user")    // 获取前缀user的属性
public class UserProperty {

    private String userName;
    private int userAge;

}
