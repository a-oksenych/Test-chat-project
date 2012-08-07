package com.copyright.rup.chat.account;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
//import org.apache.ibatis.annotations.Many;
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
    final String SET_PATH = "SET search_path TO chat_db; ";
    final String CREATE_ACCOUNT = "INSERT INTO account(name, email, password) VALUES (#{name}, #{email}, #{password});";
    final String GET_INSERTED_ID = "SELECT CURRVAL('account_uid_seq')";
    final String GET_ACCOUNT = "SELECT account_uid, name, email, password FROM account WHERE account_uid = #{idAccount}";
    final String UPDATE_ACCOUNT = "UPDATE account SET name = #{name}, email = #{email}, password = #{password} WHERE account_uid = #{id}";
    //rewrite
    final String DELETE_ACCOUNT = "DELETE FROM account WHERE account_uid = #{idAccount} ";//UNION account_2_room ON account.account_uid = account_2_room.account_uid WHERE account_uid = #{idAccount}";
    final String GET_ROOMS_FOR_ACCOUNT = "SELECT room_uid, name, description FROM room where room_uid IN (SELECT room_uid FROM account_2_room WHERE account_uid = #{idAccount})";
    
    @Insert(SET_PATH + CREATE_ACCOUNT)  
    public Integer createAccountMyBatis(Account account) throws Exception;
    
    @Select(GET_INSERTED_ID)    
    public Integer getInsertedId() throws Exception;
    
    @Select(SET_PATH + GET_ACCOUNT)
    @Results(value = {
        @Result(property="id", column="account_uid"),
        @Result(property="name", column="name"),
        @Result(property="email", column="email"),
        @Result(property="password", column="password"),
//      @Result(property="rooms", column="id", javaType=Room.class, many="getRoomsForAccount"),
    })
    public Account getAccount(int idAccount) throws Exception;
    
    @Update(SET_PATH + UPDATE_ACCOUNT)
    public Integer updateAccount(Account account) throws Exception;
    
    @Delete(SET_PATH + DELETE_ACCOUNT)
    public void deleteAccount(int idAccount) throws Exception;
    
    @Select(SET_PATH + GET_ROOMS_FOR_ACCOUNT)
    public List<Room> getRoomsForAccount(int idAccount) throws Exception;
}