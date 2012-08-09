package com.copyright.rup.chat.account;

import com.copyright.rup.chat.account.AccountException.Type;
import com.copyright.rup.chat.common.Account;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Oleksandr Dekhtyar
 * 
 */
public class DAOAccountStub implements IDAOAccount {

    private int idx = 0;
    private Map<Integer, Account> accounts = new HashMap<Integer, Account>();

    @Override
    public Account createAccount(Account account) {
        account.setId(++idx);
        accounts.put(account.getId(), account);
        return account;
    }

    @Override
    public Account getAccount(int idAccount) {
        return accounts.get(idAccount);
    }

    @Override
    public Account updateAccount(Account account) {
        if (!accounts.containsKey(account.getId())) {
            throw new AccountException(Type.NOT_FOUND, "Account not found");
        }
        accounts.put(account.getId(), account);
        return account;
    }

    @Override
    public void deleteAccount(int id) {
        accounts.remove(id);
    }
}
