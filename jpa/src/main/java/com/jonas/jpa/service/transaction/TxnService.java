package com.jonas.jpa.service.transaction;

import com.jonas.jpa.repository.mysql.bean.entity.Txn1;
import com.jonas.jpa.repository.mysql.bean.entity.Txn2;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author shenjy
 * @createTime 2023/5/31 19:40
 * @description
 */
@Service
@RequiredArgsConstructor
public class TxnService {
    private final Txn1Service txn1Service;
    private final Txn2Service txn2Service;

    /**
     * 场景一：外围方法没有开启事务
     * <p>
     * 张三和李四保存成功，说明在外围方法未开启事务的情况下Propagation.REQUIRED修饰的内部方法会新开启自己的事务，
     * 且开启的事务相互独立，互不干扰。
     */
    public void notransaction_exception_required_required() {
        Txn1 txn1 = new Txn1();
        txn1.setName("张三");
        txn1Service.addRequired(txn1);

        Txn2 txn2 = new Txn2();
        txn2.setName("李四");
        txn2Service.addRequired(txn2);
        // 此处抛出异常不会影响到txn1和txn2事务的执行
        // 因为外围没有事务
        throw new RuntimeException();
    }

    /**
     * 张三保存成功，李四回滚
     */
    public void notransaction_required_required_exception() {
        Txn1 user1 = new Txn1();
        user1.setName("张三");
        txn1Service.addRequired(user1);

        Txn2 user2 = new Txn2();
        user2.setName("李四");
        txn2Service.addRequiredException(user2);
    }
}
