package com.jonas.jpa.repository.mysql.bean.req;

import lombok.Data;

import javax.validation.constraints.*;

/**
 * @author shenjy
 * @createTime 2022/10/14 15:51
 * @description UserCreateReq
 */
@Data
public class UserCreateReq {
    @NotBlank(message = "用户名不能为空")
    private String name;
    @Min(0)
    @Max(100)
    @NotNull(message = "年龄不能为空")
    private Integer age;
    private Integer sex;
    private String language;
}
