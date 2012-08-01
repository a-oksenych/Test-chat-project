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

public interface IInfoMapper {
	final String CREATE_ACCOUNT = "INSERT INTO account(name, email, password) VALUES (#{name}, #{email}, #{password})";
	final String GET_ACCOUNT = "SELECT id, name, email, password FROM account WHERE id = #{idAccount}";
	final String UPDATE_ACCOUNT = "UPDATE account SET name = #{name}, email = #{email}, password = #{password} WHERE id = #{id}";
	final String DELETE_ACCOUNT = "DELETE FROM account JOIN room_account ON account.id = room_account.id_account WHERE id = #{idAccount}";
	final String GET_ROOMS_FOR_ACCOUNT = "SELECT id, name, description FROM room where id IN (SELECT id_room FROM room_account WHERE id_account = #{idAccount})";
	
	@Insert(CREATE_ACCOUNT)
	public Account createAccount(Account account) throws Exception;
	
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