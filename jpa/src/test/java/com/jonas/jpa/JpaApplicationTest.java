package com.jonas.jpa;

import com.jonas.jpa.service.transaction.TxnRequiredService;
import com.jonas.jpa.service.transaction.TxnService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author shenjy
 * @createTime 2023/5/31 19:48
 * @description
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class JpaApplicationTest {

    @Autowired
    private TxnRequiredService txnRequiredService;
    @Autowired
    private TxnService txnService;

    @Test
    public void notransaction_exception_required_required() {
        txnRequiredService.notransaction_exception_required_required();
    }

    @Test
    public void notransaction_required_required_exception() {
        txnRequiredService.notransaction_required_required_exception();
    }

    @Test
    public void transaction_exception_required_required() {
        txnRequiredService.transaction_exception_required_required();
    }

    @Test
    public void transaction_required_required_exception() {
        txnRequiredService.transaction_required_required_exception();
    }

    @Test
    public void transaction_required_required_exception_try() {
        txnRequiredService.transaction_required_required_exception_try();
    }

    @Test
    public void transaction_no_exception() {
        txnService.transaction_no_exception();
    }

    @Test
    public void transaction_no_exception_try() {
        txnService.transaction_no_exception_try();
    }

    @Test
    public void transaction_find() {
        txnService.transaction_find();
    }
}
