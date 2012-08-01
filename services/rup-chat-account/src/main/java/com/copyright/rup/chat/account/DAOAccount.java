package com.copyright.rup.chat.account;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.log4j.Logger;

import com.copyright.rup.chat.common.Account;
import com.copyright.rup.chat.common.MyBatisConnectionFactory;
import com.copyright.rup.chat.common.Room;

/**
 * @author Andriy Oksenych
 * 
 */
public class DAOAccount implements IDAOAccount, IInfoMapper {
	Logger logger = Logger.getLogger(DAOAccountStub.class);

    @Override
    public Account createAccount(Account account) {
    	Account createdAccount = null;
        SqlSession session = getSession();
		try {
			IInfoMapper mapper = session.getMapper(IInfoMapper.class);
			createdAccount = mapper.createAccount(account);
		} catch (Exception e) {
			logger.error("Can't create an account");
		} finally {
			session.close();
		}
        
        return createdAccount;
    }

    @Override
    public Account getAccount(int idAccount) {
    	Account selectedAccount = null;
    	SqlSession session = getSession();
		try {
			IInfoMapper mapper = session.getMapper(IInfoMapper.class);
			selectedAccount = mapper.getAccount(idAccount);
		} catch (Exception e) {
			logger.error("Can't select an account");
		} finally {
			session.close();
		}
		
		return selectedAccount;
    }

    @Override
    public Account updateAccount(Account account) {
    	Account updatedAccount = null;
    	SqlSession session = getSession();
		try {
			IInfoMapper mapper = session.getMapper(IInfoMapper.class);
			updatedAccount = mapper.updateAccount(account);
		} catch (Exception e) {
			logger.error("Can't update an account");
		} finally {
			session.close();
		}
		
		return updatedAccount;
    }

    @Override
    public void deleteAccount(int idAccount) {
    	SqlSession session = getSession();
		try {
			IInfoMapper mapper = session.getMapper(IInfoMapper.class);
			mapper.deleteAccount(idAccount);
		} catch (Exception e) {
			logger.error("Can't delete an account");
		} finally {
			session.close();
		}
    }
    
    @Override
    public List<Room> getRoomsForAccount(int idAccount) {
    	List<Room> rooms = null;
    	SqlSession session = getSession();
		try {
			IInfoMapper mapper = session.getMapper(IInfoMapper.class);
			rooms = mapper.getRoomsForAccount(idAccount);
		} catch (Exception e) {
			logger.error("Can't delete an account");
		} finally {
			session.close();
		}
		
		return rooms;
    }
    
    private SqlSession getSession() {
		SqlSessionFactory sqlSessionFactory = MyBatisConnectionFactory
				.getSqlSessionFactory();
		return sqlSessionFactory.openSession();
	}
}
