package com.jonas.jpa.repository.mysql.dao;

import com.jonas.jpa.repository.mysql.bean.entity.User;
import org.springframework.data.repository.CrudRepository;

/**
 * @author shenjy
 * @createTime 2022/10/13 20:36
 * @description UserDao
 */
public interface UserDao extends CrudRepository<User, Long> {

}
