package com.ddh.store.dao.daoImp;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.ddh.store.dao.CategoryDao;
import com.ddh.store.domain.Category;
import com.ddh.store.utils.JDBCUtils;

public class CategoryDaoImp implements CategoryDao {

	public List<Category> findAllCats() throws SQLException {
		String sql="select * from category where cid not LIKE '%unUse%'";
		QueryRunner qr=new QueryRunner(JDBCUtils.getDataSource());
		return qr.query(sql, new BeanListHandler<Category>(Category.class));		
	}

	@Override
	public void addCategory(Category category) throws Exception {
		// TODO Auto-generated method stub
		String sql="insert into category values (?,?) ";
		QueryRunner qr=new QueryRunner(JDBCUtils.getDataSource());
		qr.update(sql,category.getCid(),category.getCname());		
	}

	@Override
	public Category findOneCat(String cid) throws Exception {
		String sql="select * from category where cid=?";
		QueryRunner qr=new QueryRunner(JDBCUtils.getDataSource());
		return qr.query(sql, new BeanHandler<Category>(Category.class),cid);
	}

	@Override
	public void deletOneCat(String cid) throws Exception {
		// TODO Auto-generated method stub
		String sql="UPDATE category SET cid ='unUse"+cid+"'where cid=?";
		QueryRunner qr=new QueryRunner(JDBCUtils.getDataSource());
		qr.update(sql,cid);
	}



}
