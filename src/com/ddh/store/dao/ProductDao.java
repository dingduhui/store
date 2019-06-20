package com.ddh.store.dao;

import java.util.List;

import com.ddh.store.domain.PageModel;
import com.ddh.store.domain.Product;

public interface ProductDao {

	List<Product> findHots() throws Exception;
	List<Product> findNews() throws Exception;
	Product findProductBypid(String pid) throws Exception;
	int findTotalRecords(String cid) throws Exception;
	List findProductsByCidWithPage(String cid, int startIndex, int pageSize) throws Exception;
	PageModel findAllProductCatsWithPage(int curNum) throws Exception;
	int findTotalRecords()throws Exception;
	List<Product> findAllProductCatsWithPage(int startIndex, int pageSize)throws Exception;
	void saveProduct(Product product)throws Exception;
}
