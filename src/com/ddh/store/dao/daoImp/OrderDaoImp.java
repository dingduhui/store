package com.ddh.store.dao.daoImp;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.ddh.store.dao.OrderDao;
import com.ddh.store.domain.Order;
import com.ddh.store.domain.OrderItem;
import com.ddh.store.domain.Product;
import com.ddh.store.domain.User;
import com.ddh.store.utils.JDBCUtils;

public class OrderDaoImp implements OrderDao {

	@Override
	public void saveOrderItem(Connection conn, OrderItem item) throws Exception {
		// TODO Auto-generated method stub
		String sql = "INSERT INTO orderitem VALUES(?,?,?,?,?)";
		QueryRunner qr = new QueryRunner();
		Object[] params = { item.getItemid(), item.getQuantity(), item.getTotal(), item.getProduct().getPid(),
				item.getOrder().getOid() };
		qr.update(conn, sql, params);
	}

	@Override
	public void saveOrder(Connection conn, Order order) throws Exception {
		// TODO Auto-generated method stub
		String sql = "INSERT INTO orders VALUES(?,?,?,?,?,?,?,?)";
		QueryRunner qr = new QueryRunner();
		Object[] params = { order.getOid(), order.getOrdertime(), order.getTotal(), order.getState(),
				order.getAddress(), order.getName(), order.getTelephone(), order.getUser().getUid() };
		qr.update(conn, sql, params);
	}

	@Override
	public int getTotalRecords(User user) throws Exception {
		String sql = "select count(*) from orders where uid=?";
		QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
		Long num = (Long) qr.query(sql, new ScalarHandler(), user.getUid());
		return num.intValue();
	}

	@Override
	public List<Order> findOrdersWithPage(User user, int startIndex, int pageSize) throws Exception {
		String sql = "SELECT * FROM orders WHERE uid =?  ORDER BY ordertime DESC LIMIT  ? , ?";
		QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
		List<Order> list = qr.query(sql, new BeanListHandler<Order>(Order.class), user.getUid(), startIndex, pageSize);
		for (Order order : list) {
			sql = "SELECT * FROM  orderitem o , product p WHERE o.pid=p.pid  AND oid=?";
			List<Map<String, Object>> list01 = qr.query(sql, new MapListHandler(), order.getOid());
			for (Map<String, Object> map : list01) {
				Product product = new Product();
				OrderItem OrderItem = new OrderItem();
				try {
					// BeanUtils会自动将map上属于product对象中的数据填充到product对象上
					// BeanUtils会自动将map上属于OrderItem对象中的数据填充到OrderItem对象上
					BeanUtils.populate(product, map);
					BeanUtils.populate(OrderItem, map);
				} catch (Exception e) {
					e.printStackTrace();
				}

				OrderItem.setProduct(product);
				order.getList().add(OrderItem);
			}
		}
		return list;
	}

	@Override
	public Order findOrderByOid(String oid) throws Exception {
		// TODO Auto-generated method stub
		String sql = "select * from orders where oid=?";
		QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
		Order order = qr.query(sql, new BeanHandler<Order>(Order.class), oid);
		// 根据订单id查询订单下所有商品
		sql = "select * from orderitem o,product p where o.pid=p.pid and oid=?";
		List<Map<String, Object>> list = qr.query(sql, new MapListHandler(), oid);
		for (Map<String, Object> map : list) {
			Product Product = new Product();
			OrderItem OrderItem = new OrderItem();
			try {
				BeanUtils.populate(Product, map);
				BeanUtils.populate(OrderItem, map);
			} catch (Exception e) {
				e.printStackTrace();
			}

			OrderItem.setProduct(Product);
			order.getList().add(OrderItem);
		}
		return order;
	}

	@Override
	public void updateOrder(Order order) throws Exception {
		// TODO Auto-generated method stub
		String sql = "UPDATE orders SET ordertime=?, total=?, state=? ,address=?, name=?,telephone=? where oid=?";
		Object[] params = { order.getOrdertime(), order.getTotal(), order.getState(), order.getName(),
				order.getAddress(), order.getTelephone(), order.getOid() };
		QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
		qr.update(sql,params);
	}

	@Override
	public List<Order> findAllOrders() throws Exception {
		// TODO Auto-generated method stub
		String sql = "select * from orders";
		QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
		return qr.query(sql, new BeanListHandler<Order>(Order.class));
	}

	@Override
	public List<Order> findAllOrders(String state) throws Exception {
				String sql = "select * from orders where state=?";
				QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
				return qr.query(sql, new BeanListHandler<Order>(Order.class),state);
		
	}

}
