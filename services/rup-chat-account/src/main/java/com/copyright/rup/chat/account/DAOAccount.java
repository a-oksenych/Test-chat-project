package com.copyright.rup.chat.account;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.log4j.Logger;

import com.copyright.rup.chat.common.Account;
import com.copyright.rup.chat.common.MyBatisConnectionFactory;

/**
 * @author Andriy Oksenych
 * 
 */
public class DAOAccount implements IDAOAccount {
	Logger logger = Logger.getLogger(DAOAccountStub.class);
    
    public interface InfoMapper {
    	final String CREATE_ACCOUNT = "INSERT INTO account(name, email, password) VALUES (#{name}, #{email}, #{password})";
    	final String GET_ACCOUNT = "SELECT * FROM ACCOUNT WHERE id = #{idAccount}";
    	final String UPDATE_ACCOUNT = "UPDATE account SET name = #{name}, email = #{email}, password = #{password} WHERE id = #{id}";
    	final String DELETE_ACCOUNT = "DELETE FROM account WHERE id = #{id}";
    	
    	@INSERT(CREATE_ACCOUNT)
    	public void createAccount(Account account) throws Exception;
    	
    	@SELECT(GET_ACCOUNT)
    	public Account getAccount(int idAccount) throws Exception;
    	
    	@UPDATE(UPDATE_ACCOUNT)
    	public void updateAccount(Account account) throws Exception;
    	
    	@DELETE(DELETE_ACCOUNT)
    	public void deleteAccount(int id) throws Exception;
    }

    @Override
    public Account createAccount(Account account) {
        SqlSession session = getSession();
		try {
			InfoMapper mapper = session.getMapper(InfoMapper.class);
			mapper.insertInformation(account);
		} catch (Exception e) {
			logger.error("Can't create an account");
		} finally {
			session.close();
		}
        
        return account;
    }

    @Override
    public Account getAccount(int idAccount) {
    	SqlSession session = getSession();
		try {
			InfoMapper mapper = session.getMapper(InfoMapper.class);
			return mapper.getAccount(idAccount);
		} catch (Exception e) {
			logger.error("Can't create an account");
		} finally {
			session.close();
		}
    }

    @Override
    public Account updateAccount(Account account) {
    	SqlSession session = getSession();
		try {
			InfoMapper mapper = session.getMapper(InfoMapper.class);
			mapper.updateAccount(account);
		} catch (Exception e) {
			logger.error("Can't create an account");
		} finally {
			session.close();
		}
    }

    @Override
    public void deleteAccount(int id) {
    	SqlSession session = getSession();
		try {
			InfoMapper mapper = session.getMapper(InfoMapper.class);
			mapper.deleteAccount(id);
		} catch (Exception e) {
			logger.error("Can't create an account");
		} finally {
			session.close();
		}
    }
    
    private SqlSession getSession() {
		SqlSessionFactory sqlSessionFactory = MyBatisConnectionFactory
				.getSqlSessionFactory();
		return sqlSessionFactory.openSession();
	}
}
