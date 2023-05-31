package com.jonas.jpa.service.transaction;

import com.jonas.jpa.repository.mysql.bean.entity.Txn1;
import com.jonas.jpa.repository.mysql.bean.entity.Txn2;
import com.jonas.jpa.repository.mysql.dao.Txn2Dao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


/**
 * @author shenjy
 * @createTime 2023/5/25 17:02
 * @description 事务管理器
 */
@Service
@RequiredArgsConstructor
public class Txn2Service {

    private final Txn2Dao txn2Dao;

    @Transactional(propagation = Propagation.REQUIRED)
    public void addRequired(Txn2 user) {
        txn2Dao.save(user);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void addRequiredException(Txn2 user) {
        txn2Dao.save(user);
        throw new RuntimeException();
    }
}
