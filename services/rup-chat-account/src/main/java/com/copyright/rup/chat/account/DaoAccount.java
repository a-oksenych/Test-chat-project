package com.copyright.rup.chat.account;

import com.copyright.rup.chat.common.Account;
import com.copyright.rup.chat.common.Room;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Andriy Oksenych
 * 
 */
@Repository
public class DaoAccount implements IDAOAccount {

    private Logger logger = Logger.getLogger(DaoAccount.class);
    private IInfoMapper mapper;

    @Override
    public Account createAccount(Account account) {
        try {
            mapper.createAccount(account);
            //account.setId(mapper.getInsertedId());
        } catch (Exception e) {
            logger.error("can't create an account" + e);
        }

        return account;
    }

    @Override
    public Account getAccountById(int idAccount) {
        Account account = null;
        try {
            account = mapper.getAccountById(idAccount);
        } catch (Exception e) {
            logger.error("can't select an account" + e);
        }

        return account;
    }

    @Override
    public Account updateAccount(Account account) {
        try {
            mapper.updateAccount(account);
        } catch (Exception e) {
            logger.error("can't update an account" + e);
        }

        return account;
    }

    @Override
    public void deleteAccount(int idAccount) {
        try {
            mapper.deleteAccount(idAccount);
        } catch (Exception e) {
            logger.error("can't delete an account" + e);
        }
    }

    public List<Room> getRoomsForAccount(int idAccount) {
        List<Room> rooms = null;
        try {
            rooms = mapper.getRoomsForAccount(idAccount);
        } catch (Exception e) {
            logger.error("can't select rooms for the account " + idAccount + " " + e);
        }

        return rooms;
    }

    // Setters & Getters
    public IInfoMapper getMapper() {
        return mapper;
    }

    public void setMapper(IInfoMapper mapper) {
        this.mapper = mapper;
    }
}
