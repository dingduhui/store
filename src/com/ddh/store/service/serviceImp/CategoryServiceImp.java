package com.ddh.store.service.serviceImp;

import java.util.List;

import com.ddh.store.dao.CategoryDao;
import com.ddh.store.dao.daoImp.CategoryDaoImp;
import com.ddh.store.domain.Category;
import com.ddh.store.service.CategoryService;

public class CategoryServiceImp implements CategoryService {

	@Override
	public List<Category> findAllCats() throws Exception {
	  CategoryDao categoryDao=new CategoryDaoImp();
	  return categoryDao.findAllCats();
		
	}

	@Override
	public void addCategory(Category category) throws Exception {
		// TODO Auto-generated method stub
		CategoryDao categoryDao=new CategoryDaoImp();
		  categoryDao.addCategory(category);
	}

	@Override
	public Category findOneCat(String cid) throws Exception {
		CategoryDao categoryDao=new CategoryDaoImp();
		return   categoryDao.findOneCat(cid);
	
	}

	@Override
	public void deletOneCat(String cid) throws Exception {
		// TODO Auto-generated method stub
		CategoryDao categoryDao=new CategoryDaoImp();
		  categoryDao.deletOneCat(cid);
	}





}
