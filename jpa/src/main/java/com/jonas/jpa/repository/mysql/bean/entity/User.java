package com.jonas.jpa.repository.mysql.bean.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;

/**
 * @author shenjy
 * @createTime 2022/10/13 20:39
 * @description User
 */
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    private Long id;

    private String name;

    private Integer age;

    private Integer sex = Sex.MALE;

    private Integer status = Status.NORMAL;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    public interface Status {
        int NORMAL = 1;
        int DELETED = 0;
    }

    public interface Sex {
        int MALE = 1;
        int FEMALE = 0;
    }
}
