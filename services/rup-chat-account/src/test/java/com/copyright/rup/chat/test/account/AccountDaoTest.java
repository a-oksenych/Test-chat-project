package com.copyright.rup.chat.test.account;

import java.io.File;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.copyright.rup.chat.account.DaoAccount;
import com.copyright.rup.chat.common.Account;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/spring-context.xml" })
public class AccountDaoTest {
    @Autowired
    private DaoAccount accountDao;

    @Test
    public void simpleTest() {
        Account account = new Account();
        account.setName("Dexter");
        account.setPassword("DexterASD");
        account.setEmail("Dexter@com.fr");
        accountDao.createAccount(account);
        
        Account resultAccount = accountDao.getAccountById(1); 
        System.out.println(resultAccount);
    }
}
