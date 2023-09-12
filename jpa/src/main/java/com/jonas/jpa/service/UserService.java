package com.jonas.jpa.service;

import com.jonas.jpa.common.CacheConst;
import com.jonas.jpa.config.LocaleHandler;
import com.jonas.jpa.repository.mysql.bean.entity.User;
import com.jonas.jpa.repository.mysql.bean.req.UserCreateReq;
import com.jonas.jpa.repository.mysql.bean.view.UserView;
import com.jonas.jpa.repository.mysql.dao.UserDao;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author shenjy
 * @createTime 2022/10/13 20:53
 * @description UserService
 */
@Service
@RequiredArgsConstructor
public class UserService {
    private final UserDao userDao;
    private final LocaleHandler localeHandler;

    @Cacheable(value = CacheConst.USER)
    public UserView getUser(long userId) {
        User user = userDao.findById(userId).orElse(null);
        return buildUserView(user);
    }

    public List<UserView> listUserByAgeLessThan(int age) {
        List<User> users = userDao.findByAgeLessThan(age);
        return buildUserViews(users);
    }

    public UserView createUser(UserCreateReq req) {
        User user = User.builder()
                .name(localeHandler.getMessageByServer(req.getName()))
                .age(req.getAge()).sex(req.getSex())
                .status(User.Status.NORMAL)
                .locale(req.getLanguage())
                .createTime(LocalDateTime.now())
                .updateTime(LocalDateTime.now())
                .build();
        userDao.save(user);
        return buildUserView(user);
    }

    private UserView buildUserView(User user) {
        if (user == null) {
            return null;
        }
        UserView userView = UserView.builder().id(user.getId()).name(user.getName()).age(user.getAge())
                .sex(user.getSex()).status(user.getStatus()).createTime(user.getCreateTime())
                .updateTime(user.getUpdateTime()).build();
        return userView;
    }

    private List<UserView> buildUserViews(List<User> users) {
        if (CollectionUtils.isEmpty(users)) {
            return new ArrayList<>();
        }
        return users.stream().map(this::buildUserView).collect(Collectors.toList());
    }
}
