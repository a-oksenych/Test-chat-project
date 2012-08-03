package com.copyright.rup.chat.account;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.Delete;

import com.copyright.rup.chat.common.Account;
import com.copyright.rup.chat.common.Room;

/**
 * @author Andriy Oksenych
 * 
 */
public interface IInfoMapper {
	final String CREATE_ACCOUNT = "SET search_path TO chat_db; INSERT INTO account(name, email, password) VALUES (#{name}, #{email}, #{password}); SELECT CURRVAL('account_uid_seq')";
	final String GET_ACCOUNT = "SELECT account_uid, name, email, password FROM chat_db.account WHERE account_uid = #{idAccount}";
	final String UPDATE_ACCOUNT = "UPDATE chat_db.account SET name = #{name}, e-mail = #{email}, password = #{password} WHERE account_uid = #{id}";
	final String DELETE_ACCOUNT = "DELETE FROM chat_db.account JOIN chat_db.account_2_room ON chat_db.account.account_uid = chat_db.account_2_room.account_uid WHERE account_uid = #{idAccount}";
	final String GET_ROOMS_FOR_ACCOUNT = "SELECT room_uid, name, description FROM chat_db.room where room_uid IN (SELECT room_uid FROM chat_db.account_2_room WHERE account_uid = #{idAccount})";
	
	@Insert(CREATE_ACCOUNT)
	public Integer createAccountMyBatis(Account account) throws Exception;
	
	@Select(GET_ACCOUNT)
	@Results(value = {
		@Result(property="id", column="id"),
		@Result(property="name", column="name"),
		@Result(property="email", column="email"),
		@Result(property="password", column="password"),
//		@Result(property="rooms", column="id", javaType=Room.class, many="getRoomsForAccount"),
	})
	public Account getAccount(int idAccount) throws Exception;
	
	@Update(UPDATE_ACCOUNT)
	public Account updateAccount(Account account) throws Exception;
	
	@Delete(DELETE_ACCOUNT)
	public void deleteAccount(int idAccount) throws Exception;
	
	@Select(GET_ROOMS_FOR_ACCOUNT)
	public List<Room> getRoomsForAccount(int idAccount) throws Exception;
}