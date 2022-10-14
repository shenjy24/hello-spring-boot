package com.jonas.jpa.repository.mysql.bean.req;

import lombok.Data;

/**
 * @author shenjy
 * @createTime 2022/10/14 15:51
 * @description UserCreateReq
 */
@Data
public class UserCreateReq {
    private String name;
    private Integer age;
    private Integer sex;
}
