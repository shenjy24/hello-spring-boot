package com.jonas.jpa.service;

import com.jonas.jpa.repository.mysql.bean.entity.User;
import com.jonas.jpa.repository.mysql.dao.UserDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author shenjy
 * @createTime 2022/10/13 20:53
 * @description UserService
 */
@Service
@RequiredArgsConstructor
public class UserService {
    private final UserDao userDao;

    public User getUser(long userId) {
        return userDao.findById(userId).orElse(null);
    }
}
