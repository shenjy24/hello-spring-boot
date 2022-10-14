package com.jonas.jpa.repository.mysql.bean.view;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @author shenjy
 * @createTime 2022/10/14 15:54
 * @description UserView
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserView {
    private Long id;
    private String name;
    private Integer age;
    private Integer sex;
    private Integer status;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
