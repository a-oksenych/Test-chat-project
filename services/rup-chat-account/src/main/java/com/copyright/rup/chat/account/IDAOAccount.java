package com.copyright.rup.chat.account;

import com.copyright.rup.chat.common.Account;

/**
 * @author Oleksandr Dekhtyar
 * 
 */
public interface IDAOAccount {

    public Account createAccount(Account account);

    public Account getAccount(int id);

    public Account updateAccount(Account account);

    public void deleteAccount(int id);
}
