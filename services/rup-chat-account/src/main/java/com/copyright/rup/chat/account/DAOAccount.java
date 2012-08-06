package com.copyright.rup.chat.account;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.log4j.Logger;

import com.copyright.rup.chat.common.Account;
import com.copyright.rup.chat.common.Room;

/**
 * @author Andriy Oksenych
 * 
 */
public class DaoAccount implements IDAOAccount, IInfoMapper {
	private Logger logger = Logger.getLogger(DaoAccount.class);
	private SqlSessionFactory sqlSessionFactory;
	
    //IDAOAccount interface
    @Override
    public Account createAccount(Account account) {
    	account.setId(createAccountMyBatis(account));
        
        return account;
    }

    @Override
    public Account getAccount(int idAccount) {
    	Account selectedAccount = null;
    	SqlSession session = sqlSessionFactory.openSession();
		try {
			IInfoMapper mapper = session.getMapper(IInfoMapper.class);
			selectedAccount = mapper.getAccount(idAccount);
		} catch (Exception e) {
			logger.error("Can't select an account" + e);
		} finally {
			session.close();
		}
		
		return selectedAccount;
    }

    @Override
    public Account updateAccount(Account account) {
    	Account updatedAccount = null;
    	SqlSession session = sqlSessionFactory.openSession();
		try {
			IInfoMapper mapper = session.getMapper(IInfoMapper.class);
			updatedAccount = mapper.updateAccount(account);
		} catch (Exception e) {
			logger.error("Can't update an account" + e);
		} finally {
			session.close();
		}
		
		return updatedAccount;
    }

    @Override
    public void deleteAccount(int idAccount) {
    	SqlSession session = sqlSessionFactory.openSession();
		try {
			IInfoMapper mapper = session.getMapper(IInfoMapper.class);
			mapper.deleteAccount(idAccount);
		} catch (Exception e) {
			logger.error("Can't delete an account" + e);
		} finally {
			session.close();
		}
    }
    
    @Override
    public List<Room> getRoomsForAccount(int idAccount) {
    	List<Room> rooms = null;
    	SqlSession session = sqlSessionFactory.openSession();
		try {
			IInfoMapper mapper = session.getMapper(IInfoMapper.class);
			rooms = mapper.getRoomsForAccount(idAccount);
		} catch (Exception e) {
			logger.error("Can't select rooms for account" + e);
		} finally {
			session.close();
		}
		
		return rooms;
    }
    
    //IInfoMapper interface
    @Override
    public Integer createAccountMyBatis(Account account) {
        SqlSession session = sqlSessionFactory.openSession();
        Integer account_uid = null;
		try {
			IInfoMapper mapper = session.getMapper(IInfoMapper.class);
			account_uid = mapper.createAccountMyBatis(account);
		} catch (Exception e) {
			logger.error("Can't create an account " + e);
		} finally {
			session.close();
		}
        
        return account_uid;
    }
    
    //Setters & Getters
    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
    	this.sqlSessionFactory = sqlSessionFactory;
    }
}
