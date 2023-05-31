package com.jonas.jpa.service;

import com.jonas.jpa.config.LocaleHandler;
import com.jonas.jpa.repository.mysql.bean.entity.Score;
import com.jonas.jpa.repository.mysql.bean.entity.User;
import com.jonas.jpa.repository.mysql.dao.ScoreDao;
import com.jonas.jpa.repository.mysql.dao.UserDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author shenjy
 * @createTime 2023/5/30 16:18
 * @description
 */
@Service
@RequiredArgsConstructor
public class ScoreService {
    private final UserDao userDao;
    private final ScoreDao scoreDao;
    private final LocaleHandler localeHandler;

    /**
     * @param userId
     * @return
     */
    public List<Score> listScore(long userId, String course, String locale) {
        User user = userDao.findById(userId).orElse(null);
        if (user != null && !user.getLocale().equalsIgnoreCase(locale)) {
            user.setLocale(locale);
            userDao.save(user);
            List<Score> scores = scoreDao.findByUserId(userId);
            for (Score score : scores) {
                score.setCourse(localeHandler.getMessageByServer(score.getCourseKey()));
            }
            scoreDao.saveAll(scores);
            return scores;
        }
        return new ArrayList<>();
    }
}
