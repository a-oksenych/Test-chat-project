package com.copyright.rup.chat.account;

import java.util.HashMap;
import java.util.Map;

import com.copyright.rup.chat.common.Account;

/**
 * @author Oleksandr Dekhtyar
 * 
 */
public class DAOAccountStub implements IDAOAccount {

    private int id = 0;
    private Map<Integer, Account> accounts = new HashMap<Integer, Account>();

    @Override
    public Account createAccount(Account account) {
        account.setId(++id);
        accounts.put(account.getId(), account);
        return account;
    }

    @Override
    public Account getAccount(int idAccount) {
        return accounts.get(idAccount);
    }

    @Override
    public Account updateAccount(Account account) {
        accounts.put(account.getId(), account);
        return account;
    }

    @Override
    public void deleteAccount(int id) {
        accounts.remove(id);
    }
}
