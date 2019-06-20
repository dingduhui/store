package com.ddh.store.service;

import java.util.List;

import com.ddh.store.domain.PageModel;
import com.ddh.store.domain.Product;

public interface ProductService {

	List<Product> findHots()throws Exception;
	List<Product> findNews()throws Exception;
	Product findProductBypid(String pid) throws Exception;
	PageModel findProductsByCidWithPage(String cid, int curNum) throws Exception;
	PageModel findAllProductCatsWithPage(int curNum)throws Exception;
	void saveProduct(Product product)throws Exception;
}
