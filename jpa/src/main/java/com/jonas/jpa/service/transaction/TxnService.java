package com.jonas.jpa.service.transaction;

import com.jonas.jpa.repository.mysql.bean.entity.Txn1;
import com.jonas.jpa.repository.mysql.bean.entity.Txn2;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author shenjy
 * @createTime 2023/5/31 19:40
 * @description https://mp.weixin.qq.com/s?__biz=Mzg2OTA0Njk0OA==&mid=2247486668&idx=2&sn=0381e8c836442f46bdc5367170234abb&chksm=cea24307f9d5ca11c96943b3ccfa1fc70dc97dd87d9c540388581f8fe6d805ff548dff5f6b5b&token=1776990505&lang=zh_CN#rd
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
     * 场景一：外围方法没有开启事务
     * <p>
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

    /**
     * 场景二：外围方法开启事务
     * <p>
     * 张三和李四均回滚，外围方法开启事务，内部的REQUIRED事务会加入外围方法事务，外围方法回滚，
     * 内部方法也要回滚。
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void transaction_exception_required_required() {
        Txn1 txn1 = new Txn1();
        txn1.setName("张三");
        txn1Service.addRequired(txn1);

        Txn2 txn2 = new Txn2();
        txn2.setName("李四");
        txn2Service.addRequired(txn2);

        throw new RuntimeException();
    }

    /**
     * 场景二：外围方法开启事务
     * <p>
     * 张三和李四均回滚，外围方法开启事务，内部的REQUIRED事务会加入外围方法事务，
     * 内部方法抛出异常，外围方法感知到RuntimeException使整体事务回滚。
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void transaction_required_required_exception() {
        Txn1 txn1 = new Txn1();
        txn1.setName("张三");
        txn1Service.addRequired(txn1);

        Txn2 txn2 = new Txn2();
        txn2.setName("李四");
        txn2Service.addRequiredException(txn2);
    }

    /**
     * 场景二：外围方法开启事务
     * <p>
     * 张三和李四均回滚，外围方法开启事务，内部的REQUIRED事务会加入外围方法事务，
     * 内部方法抛出异常，即使异常被catch不被外围方法感知，整体事务依然回滚。
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void transaction_required_required_exception_try() {
        Txn1 txn1 = new Txn1();
        txn1.setName("张三");
        txn1Service.addRequired(txn1);

        Txn2 txn2 = new Txn2();
        txn2.setName("李四");
        try {
            txn2Service.addRequiredException(txn2);
        } catch (Exception e) {
            System.out.println("方法回滚");
        }
    }
}
