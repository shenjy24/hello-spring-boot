package com.jonas.jpa.repository.mysql.bean.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

/**
 * @author shenjy
 * @createTime 2023/5/30 16:14
 * @description 积分表
 */
@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Score {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;

    private String course;

    private String courseKey;

    private int score;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;
}
