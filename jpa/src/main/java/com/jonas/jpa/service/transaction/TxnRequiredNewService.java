package com.jonas.jpa.service.transaction;

import com.jonas.jpa.repository.mysql.bean.entity.Txn1;
import com.jonas.jpa.repository.mysql.bean.entity.Txn2;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * 内部方法的事务为 REQUIRED_NEW
 *
 * @author shenjy
 * @createTime 2023/5/31 19:40
 * @description https://mp.weixin.qq.com/s?__biz=Mzg2OTA0Njk0OA==&mid=2247486668&idx=2&sn=0381e8c836442f46bdc5367170234abb&chksm=cea24307f9d5ca11c96943b3ccfa1fc70dc97dd87d9c540388581f8fe6d805ff548dff5f6b5b&token=1776990505&lang=zh_CN#rd
 */
@Service
@RequiredArgsConstructor
public class TxnRequiredNewService {
    private final Txn1Service txn1Service;
    private final Txn2Service txn2Service;

    /**
     * 场景一：外围方法没有开启事务
     * <p>
     * 张三和李四保存成功，说明在外围方法未开启事务的情况下，内部方法会在自己的事务中运行，
     * 外围方法抛出异常不会影响内部方法。
     */
    public void notransaction_exception_requiredNew_requiredNew() {
        Txn1 txn1 = new Txn1();
        txn1.setName("张三");
        txn1Service.addRequiredNew(txn1);

        Txn2 txn2 = new Txn2();
        txn2.setName("李四");
        txn2Service.addRequiredNew(txn2);
        // 此处抛出异常不会影响到txn1和txn2事务的执行
        // 因为外围没有事务
        throw new RuntimeException();
    }

    /**
     * 场景一：外围方法没有开启事务
     * <p>
     * 张三保存成功，李四回滚。
     */
    public void notransaction_requiredNew_requiredNew_exception() {
        Txn1 user1 = new Txn1();
        user1.setName("张三");
        txn1Service.addRequiredNew(user1);

        Txn2 user2 = new Txn2();
        user2.setName("李四");
        txn2Service.addRequiredNewException(user2);
    }

    /**
     * 场景二：外围方法开启事务
     * <p>
     * 张三未插入，李四和王五插入成功
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void transaction_exception_required_required() {
        Txn1 txn1 = new Txn1();
        txn1.setName("张三");
        txn1Service.addRequired(txn1);

        Txn2 txn2 = new Txn2();
        txn2.setName("李四");
        txn2Service.addRequiredNew(txn2);

        Txn2 txn3 = new Txn2();
        txn3.setName("王五");
        txn2Service.addRequiredNew(txn3);

        throw new RuntimeException();
    }

    /**
     * 场景二：外围方法开启事务
     * <p>
     * 张三未保存，李四保存，王五未保存
     * 保存张三方法和外围方法一个事务，保存李四方法和保存王五方法分别在独立的事务中
     * 保存王五方法抛出异常，首先保存王五方法的事务被回滚，异常继续抛出被外被方法感知，外围方法事务也被回滚，因此张三保存失败
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void transaction_required_required_exception() {
        Txn1 txn1 = new Txn1();
        txn1.setName("张三");
        txn1Service.addRequired(txn1);

        Txn2 txn2 = new Txn2();
        txn2.setName("李四");
        txn2Service.addRequiredNew(txn2);

        Txn2 txn3 = new Txn2();
        txn3.setName("王五");
        txn2Service.addRequiredNewException(txn3);
    }

    /**
     * 场景二：外围方法开启事务
     * <p>
     * 张三和李四保存成功，王五保存失败
     * 外围方法开启事务，保存张三方法和外围方法一个事务，保存李四方法和保存王五方法分别在独立的事务中
     * 保存王五方法抛出异常，首先保存王五方法的事务被回滚，异常被捕获不会被外围方法感知，外围事务不回滚，故保存张三成功
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void transaction_required_required_exception_try() {
        Txn1 txn1 = new Txn1();
        txn1.setName("张三");
        txn1Service.addRequired(txn1);

        Txn2 txn2 = new Txn2();
        txn2.setName("李四");
        txn2Service.addRequiredNew(txn2);

        Txn2 txn3 = new Txn2();
        txn3.setName("王五");
        try {
            txn2Service.addRequiredNewException(txn3);
        } catch (Exception e) {
            System.out.println("方法回滚");
        }
    }
}
