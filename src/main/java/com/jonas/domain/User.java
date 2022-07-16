package com.jonas.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @NotBlank(message = "用户名不能为空")
    @Size(min = 6, max = 11, message = "用户名长度必须是6-11个字符")
    private String name;
    private int age;
    private List<String> hobbies;
}
