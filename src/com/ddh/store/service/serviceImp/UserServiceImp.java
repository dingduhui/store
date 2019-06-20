package com.ddh.store.service.serviceImp;

import java.sql.SQLException;

import javax.management.RuntimeErrorException;

import com.ddh.store.dao.UserDao;
import com.ddh.store.dao.daoImp.UserDaoImp;
import com.ddh.store.domain.User;
import com.ddh.store.service.UserService;

public class UserServiceImp implements UserService{

	@Override
	public boolean userRegist(User user) throws SQLException {
	UserDao userDao=new UserDaoImp();
	userDao.userRegist(user);
		return true;
	}

	@Override
	public boolean userActive(String code) throws SQLException {
		UserDao UserDao=new UserDaoImp();
		User user=UserDao.userActive(code);
		if(null!=user) {
			//根据激活码查询到用户并且修改状态
			user.setState(1);
			user.setCode(null);
			//更新数据库信息
			UserDao.updateUser(user);
			return true;
		}else {
			//激活失败
			return false;
		}
		
	}

	@Override
	public User userLogin(User user) throws SQLException {
		// 验证账户密码
		UserDao userdao=new UserDaoImp();
		User uu=userdao.userLogin(user);
		if(null==uu) {
			throw new RuntimeException("密码有误！");
		}else if(uu.getState()==0) {
			throw new RuntimeException("用户未激活！");
		}else {
			return uu;
		}		
	}

	@Override
	public boolean findName(String name) throws SQLException {
		// TODO Auto-generated method stub
		UserDao userdao=new UserDaoImp();
		return userdao.findName( name);
	}

}
