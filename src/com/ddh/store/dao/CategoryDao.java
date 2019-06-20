package com.ddh.store.dao;

import java.util.List;

import com.ddh.store.domain.Category;

public interface CategoryDao {

	List<Category> findAllCats() throws Exception;

	void addCategory(Category category)throws Exception;

	Category findOneCat(String cid)throws Exception;

	void deletOneCat(String cid)throws Exception;

}
