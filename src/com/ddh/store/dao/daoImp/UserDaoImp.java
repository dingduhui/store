package com.ddh.store.dao.daoImp;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import com.ddh.store.dao.UserDao;
import com.ddh.store.domain.User;
import com.ddh.store.utils.JDBCUtils;

public class UserDaoImp implements UserDao {

	@Override
	public void userRegist(User user) throws SQLException {//注册
		// TODO Auto-generated method stub
		String sql = "INSERT INTO USER VALUES(?,?,?,?,?,?,?,?,?)";
		Object[] params = { user.getUid(),user.getUsername(),user.getPassword(),user.getName(),
				user.getEmail(),user.getTelephone(),user.getSex(),user.getState(),user.getCode()};
	QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());		
		qr.update(sql,params);
	}

	@Override
	public User userActive(String code) throws SQLException {
		// TODO Auto-generated method stub
		String sql="select * from user where code=?";
		QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
		User user=qr.query(sql, new BeanHandler<User>(User.class),code);
		return user;
	}

	@Override
	public void updateUser(User user) throws SQLException {
		// TODO Auto-generated method stub
		String sql="update user set username=?,password=?,name=?,telephone=?,email=?,sex=?,code=?,state=? where uid=?";
		Object[] params = {user.getUsername(),user.getPassword(),user.getName(),user.getTelephone(),user.getEmail(),
				user.getSex(),user.getCode(),user.getState(),user.getUid()};
	QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());		
		qr.update(sql,params);
		
	}

	public User userLogin(User user) throws SQLException {
		String sql="select * from user where username=? and password=?";
		QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
		return qr.query(sql, new BeanHandler<User>(User.class),user.getUsername(),user.getPassword());
	}

	@Override
	public boolean findName(String name) throws SQLException {
		// TODO Auto-generated method stub
		String sql="select * from user where username=?";
		QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
		User user= qr.query(sql, new BeanHandler<User>(User.class),name);
		if(user==null){
			return true;
		}
		return false;
	}

}
