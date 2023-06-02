package com.jonas.jpa.service.transaction;

import com.jonas.jpa.repository.mysql.bean.entity.Txn1;
import com.jonas.jpa.repository.mysql.bean.entity.Txn2;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author shenjy
 * @createTime 2023/6/1 18:52
 * @description 测试内部方法没有事务的情况
 */
@Service
@RequiredArgsConstructor
public class TxnService {

    private final Txn1Service txn1Service;
    private final Txn2Service txn2Service;

    /**
     * 张三和李四均回滚
     * 外围方法有事务，内部方法无事务，内部方法默认加入外围方法的事务中
     * 内部方法抛出异常，外围方法感知到导致事务回滚
     */
    @Transactional
    public void transaction_no_exception() {
        Txn1 user1 = new Txn1();
        user1.setName("张三");
        txn1Service.add(user1);

        Txn2 user2 = new Txn2();
        user2.setName("李四");
        txn2Service.addException(user2);
    }

    /**
     * 张三和李四均保存成功
     * 外围方法有事务，内部方法无事务，内部方法默认加入外围方法的事务中
     * 部方法抛出异常，外围方法捕获了异常，事务没有回滚
     */
    @Transactional
    public void transaction_no_exception_try() {
        Txn1 user1 = new Txn1();
        user1.setName("张三");
        txn1Service.add(user1);

        Txn2 user2 = new Txn2();
        user2.setName("李四");
        try {
            txn2Service.addException(user2);
        } catch (Exception e) {
            System.out.println("回滚");
        }
    }

    /**
     * 在事务中，保存成功后调用查询方法可以获取到数据
     */
    @Transactional
    public void transaction_find() {
        Txn1 txn1 = new Txn1();
        txn1.setName("张三");
        txn1Service.add(txn1);
        System.out.println("保存成功，txn1:" + txn1);

        Txn1 txn11 = txn1Service.get(txn1.getId());
        System.out.println("获取Txn1, txn11:" + txn11);
    }
}
