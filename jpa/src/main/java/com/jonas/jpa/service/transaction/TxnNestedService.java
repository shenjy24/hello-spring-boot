package com.jonas.jpa.service.transaction;

import com.jonas.jpa.repository.mysql.bean.entity.Txn1;
import com.jonas.jpa.repository.mysql.bean.entity.Txn2;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * 内部方法的事务为 REQUIRED_NESTED
 *
 * 在外围方法开启事务的情况下，Propagation.NESTED修饰的内部方法属于外部事务的子事务，
 * 外围主事务回滚，子事务一定回滚，而内部子事务可以单独回滚而不影响外围主事务和其他子事务
 *
 * @author shenjy
 * @createTime 2023/5/31 19:40
 * @description https://mp.weixin.qq.com/s?__biz=Mzg2OTA0Njk0OA==&mid=2247486668&idx=2&sn=0381e8c836442f46bdc5367170234abb&chksm=cea24307f9d5ca11c96943b3ccfa1fc70dc97dd87d9c540388581f8fe6d805ff548dff5f6b5b&token=1776990505&lang=zh_CN#rd
 */
@Service
@RequiredArgsConstructor
public class TxnNestedService {
    private final Txn1Service txn1Service;
    private final Txn2Service txn2Service;

    /**
     * 场景一：外围方法没有开启事务
     * <p>
     * 张三和李四保存成功，在外围方法未开启事务的情况下，内部方法会在自己的事务中运行，
     * 外围方法抛出异常不会影响内部方法。
     */
    public void notransaction_exception_nested_nested() {
        Txn1 txn1 = new Txn1();
        txn1.setName("张三");
        txn1Service.addNested(txn1);

        Txn2 txn2 = new Txn2();
        txn2.setName("李四");
        txn2Service.addNested(txn2);

        throw new RuntimeException();
    }

    /**
     * 场景一：外围方法没有开启事务
     * <p>
     * 张三保存成功，李四回滚。
     */
    public void notransaction_nested_nested_exception() {
        Txn1 user1 = new Txn1();
        user1.setName("张三");
        txn1Service.addNested(user1);

        Txn2 user2 = new Txn2();
        user2.setName("李四");
        txn2Service.addNestedException(user2);
    }

    /**
     * 场景二：外围方法开启事务
     * <p>
     * 张三李四均回滚，外围方法开启事务，内部事务为外围事务的子事务
     * 外围方法回滚，内部方法也要回滚
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void transaction_exception_nested_nested() {
        Txn1 txn1 = new Txn1();
        txn1.setName("张三");
        txn1Service.addNested(txn1);

        Txn2 txn2 = new Txn2();
        txn2.setName("李四");
        txn2Service.addNested(txn2);

        throw new RuntimeException();
    }

    /**
     * 场景二：外围方法开启事务
     * <p>
     * 张三李四均回滚
     * 外围方法开启事务，内部事务为外围事务的子事务
     * 内部方法抛出异常回滚，且外围方法感知异常致使整体事务回滚
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void transaction_nested_nested_exception() {
        Txn1 txn1 = new Txn1();
        txn1.setName("张三");
        txn1Service.addNested(txn1);

        Txn2 txn2 = new Txn2();
        txn2.setName("李四");
        txn2Service.addNestedException(txn2);
    }

    /**
     * 场景二：外围方法开启事务
     * <p>
     * 张三保存成功，李四保存失败
     * 外围方法开启事务，内部事务为外围事务的子事务
     * 保存李四方法抛出异常，可以单独对子事务回滚
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void transaction_nested_nested_exception_try() {
        Txn1 txn1 = new Txn1();
        txn1.setName("张三");
        txn1Service.addNested(txn1);

        Txn2 txn2 = new Txn2();
        txn2.setName("李四");
        try {
            txn2Service.addNestedException(txn2);
        } catch (Exception e) {
            System.out.println("方法回滚");
        }
    }
}
