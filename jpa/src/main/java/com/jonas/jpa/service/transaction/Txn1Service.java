package com.jonas.jpa.service.transaction;

import com.jonas.jpa.repository.mysql.bean.entity.Txn1;
import com.jonas.jpa.repository.mysql.dao.Txn1Dao;
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
public class Txn1Service {

    private final Txn1Dao txn1Dao;

    public void add(Txn1 txn1) {
        txn1Dao.save(txn1);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void addRequired(Txn1 user){
        txn1Dao.save(user);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void addRequiredNew(Txn1 user){
        txn1Dao.save(user);
    }

    @Transactional(propagation = Propagation.NESTED)
    public void addNested(Txn1 user){
        txn1Dao.save(user);
    }
}
