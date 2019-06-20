package com.ddh.store.service;

import java.sql.SQLException;

import com.ddh.store.domain.User;

public interface UserService {

	boolean userRegist(User user) throws SQLException;

	boolean userActive(String code) throws SQLException;

	User userLogin(User user)throws SQLException;

	boolean findName(String name)throws SQLException;

}
