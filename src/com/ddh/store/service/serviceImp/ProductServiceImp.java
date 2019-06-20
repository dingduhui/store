package com.ddh.store.service.serviceImp;

import java.util.List;

import com.ddh.store.dao.ProductDao;
import com.ddh.store.dao.daoImp.ProductDaoImp;
import com.ddh.store.domain.PageModel;
import com.ddh.store.domain.Product;
import com.ddh.store.service.ProductService;

public class ProductServiceImp implements ProductService {
	ProductDao productdao=new ProductDaoImp();
	public ProductServiceImp() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<Product> findHots() throws Exception {
		// TODO Auto-generated method stub
		
		return productdao.findHots();
	}

	@Override
	public List<Product> findNews() throws Exception {
		// TODO Auto-generated method stub
		return productdao.findNews();
	}

	@Override
	public Product findProductBypid(String pid) throws Exception {
		// TODO Auto-generated method stub
		return productdao.findProductBypid(pid);
	}

	@Override
	public PageModel findProductsByCidWithPage(String cid, int curNum) throws Exception {
		// TODO Auto-generated method stub
		//创建pageMode对象，计算分页参数
		//统计总页数
		int totalRecords=productdao.findTotalRecords(cid);
		PageModel pm=new PageModel(curNum, totalRecords, 12);
		List lists=productdao.findProductsByCidWithPage(cid,pm.getStartIndex(),pm.getPageSize());
		pm.setList(lists);
		//关联url
		pm.setUrl("ProductServlet?method=findProductsByCidWithPage&cid="+cid);
		return pm;
	
	}

	@Override
	public PageModel findAllProductCatsWithPage(int curNum) throws Exception {
		// TODO Auto-generated method stub
		//创建对象
		int totalRecords=productdao.findTotalRecords();
		PageModel pm=new PageModel(curNum, totalRecords, 5);
		//关联集合
		List<Product> list=productdao.findAllProductCatsWithPage(pm.getStartIndex(),pm.getPageSize());
		pm.setList(list);
		//关联url
		pm.setUrl("AdminProductServlet?method=findAllProductCatsWithPage");
		return pm;
	}

	@Override
	public void saveProduct(Product product) throws Exception {
		// TODO Auto-generated method stub
		productdao.saveProduct(product);
	}

}
