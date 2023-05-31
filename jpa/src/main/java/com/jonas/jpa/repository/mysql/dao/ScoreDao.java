package com.jonas.jpa.repository.mysql.dao;

import com.jonas.jpa.repository.mysql.bean.entity.Score;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * @author shenjy
 * @createTime 2022/10/13 20:36
 * @description
 */
public interface ScoreDao extends PagingAndSortingRepository<Score, Long> {

    List<Score> findByUserId(long userId);

    List<Score> findByUserIdAndCourse(long userId, String course);
}
