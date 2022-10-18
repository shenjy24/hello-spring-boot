package com.jonas.jpa.repository.mongo.dao;

import com.jonas.jpa.repository.mongo.bean.entity.Game;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author shenjy
 * @createTime 2022/10/17 17:27
 * @description GameDao
 */
public interface GameDao extends MongoRepository<Game, String> {
}
