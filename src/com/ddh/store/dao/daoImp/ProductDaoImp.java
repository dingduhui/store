package com.ddh.store.dao.daoImp;

import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.ddh.store.dao.ProductDao;
import com.ddh.store.domain.PageModel;
import com.ddh.store.domain.Product;
import com.ddh.store.utils.JDBCUtils;

public class ProductDaoImp implements ProductDao {

	@Override
	public List<Product> findHots() throws Exception {
		String sql="SELECT * FROM product WHERE pflag=0 ORDER BY pdate DESC LIMIT 0 , 9";
		QueryRunner qr=new QueryRunner(JDBCUtils.getDataSource());
		return qr.query(sql, new BeanListHandler<Product>(Product.class));
	}

	@Override
	public List<Product> findNews() throws Exception {
		String sql="SELECT * FROM product WHERE pflag=0 AND is_hot= 1 ORDER BY pdate DESC LIMIT 0 , 9";
		QueryRunner qr=new QueryRunner(JDBCUtils.getDataSource());
		return qr.query(sql, new BeanListHandler<Product>(Product.class));
	}

	@Override
	public Product findProductBypid(String pid) throws Exception {
		// TODO Auto-generated method stub
		String sql="SELECT * FROM product WHERE pid=?";
		QueryRunner qr=new QueryRunner(JDBCUtils.getDataSource());
		return qr.query(sql, new BeanHandler<Product>(Product.class),pid);
	}

	@Override
	public int findTotalRecords(String cid) throws Exception {
		// TODO Auto-generated method stub
		String sql="SELECT count(*) FROM product WHERE cid=?";
		QueryRunner qr=new QueryRunner(JDBCUtils.getDataSource());
		Long num= (Long) qr.query(sql, new ScalarHandler(),cid);
		return num.intValue();
	}

	@Override
	public List findProductsByCidWithPage(String cid, int startIndex, int pageSize) throws Exception {
		String sql="SELECT * FROM product WHERE cid=? LIMIT ?,?";
		QueryRunner qr=new QueryRunner(JDBCUtils.getDataSource());
		return qr.query(sql, new BeanListHandler<Product>(Product.class),cid,startIndex,pageSize);
	}


	@Override
	public int findTotalRecords() throws Exception {
		// TODO Auto-generated method stub
		String sql="SELECT count(*) FROM product";
		QueryRunner qr=new QueryRunner(JDBCUtils.getDataSource());
		Long num= (Long) qr.query(sql, new ScalarHandler());
		return num.intValue();
	}

	@Override
	public List<Product> findAllProductCatsWithPage(int startIndex, int pageSize) throws Exception {
		// TODO Auto-generated method stub
		String sql="SELECT * FROM product order by pdate desc limit ?,?";
		QueryRunner qr=new QueryRunner(JDBCUtils.getDataSource());
		return qr.query(sql, new BeanListHandler<Product>(Product.class),startIndex,pageSize);
		
	}

	@Override
	public void saveProduct(Product product) throws Exception {
		// TODO Auto-generated method stub
		String sql="insert into product values (?,?,?,?,?,?,?,?,?,?)";
		QueryRunner qr=new QueryRunner(JDBCUtils.getDataSource());
		Object[] params= {product.getPid(),product.getPname(),product.getMarket_price(),product.getShop_price(),
				product.getPimage(),product.getPdate(),product.getIs_hot(),product.getPdesc(),
				product.getPflag(),product.getCid()};
		qr.update(sql,params);
		
	}

	@Override
	public PageModel findAllProductCatsWithPage(int curNum) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
