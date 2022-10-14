package com.jonas.jpa.repository.mysql.dao;

import com.jonas.jpa.repository.mysql.bean.entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * @author shenjy
 * @createTime 2022/10/13 20:36
 * @description UserDao
 */
public interface UserDao extends CrudRepository<User, Long> {

    List<User> findByAgeLessThan(int age);
}
