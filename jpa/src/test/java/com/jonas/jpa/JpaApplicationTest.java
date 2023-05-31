package com.jonas.jpa;

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
    private TxnService txnService;

    @Test
    public void notransaction_exception_required_required() {
        txnService.notransaction_exception_required_required();
    }

    @Test
    public void notransaction_required_required_exception() {
        txnService.notransaction_required_required_exception();
    }
}
