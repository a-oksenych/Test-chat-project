package com.copyright.rup.chat.account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.copyright.rup.chat.common.Account;

/**
 * @author Oleksandr Dekhtyar
 * 
 */
@Service
public class AccountService {

    @Autowired
    private IDAOAccount daoAccount;

    public Account createAccount(Account account) {
        return daoAccount.createAccount(account);
    }

    public Account getAccount(int id) {
        return daoAccount.getAccount(id);
    }

    public Account updateAccount(Account account) {
        return daoAccount.updateAccount(account);
    }

    public void deleteAccount(int id) {
        daoAccount.deleteAccount(id);
    }

}
