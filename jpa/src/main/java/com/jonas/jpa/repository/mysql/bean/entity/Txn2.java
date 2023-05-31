package com.jonas.jpa.repository.mysql.bean.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @author shenjy
 * @createTime 2023/5/31 19:03
 * @description 用于事务测试
 */
@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Txn2 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
}
