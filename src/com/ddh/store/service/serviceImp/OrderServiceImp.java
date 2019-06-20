package com.ddh.store.service.serviceImp;

import java.sql.Connection;
import java.util.List;

import com.ddh.store.dao.OrderDao;
import com.ddh.store.dao.daoImp.OrderDaoImp;
import com.ddh.store.domain.Order;
import com.ddh.store.domain.OrderItem;
import com.ddh.store.domain.PageModel;
import com.ddh.store.domain.User;
import com.ddh.store.service.OrderService;
import com.ddh.store.utils.JDBCUtils;

public class OrderServiceImp implements OrderService {
	OrderDao orderDao = new OrderDaoImp();
	@Override
	public void saveOrder(Order order) throws Exception {
		// TODO Auto-generated method stub
		// 开启数据库事务
		Connection conn = null;
		try {
			conn = JDBCUtils.getConnection();
			conn.setAutoCommit(false);		
			orderDao.saveOrder(conn, order);
			// 保存订单项
			for (OrderItem item : order.getList()) {
				orderDao.saveOrderItem(conn, item);
			}
			conn.commit();
		} catch (Exception e) {
			// TODO: handle exception
			conn.rollback();
		}

	}

	@Override
	public PageModel findOrdersWithPage(User user, int curNum) throws Exception {
		// TODO Auto-generated method stub
		//创建pm对象，计算并且携带分页参数
		int totalRecords=orderDao.getTotalRecords(user);
		PageModel pm=new PageModel(curNum, totalRecords, 3);
		//关联集合
		List list=orderDao.findOrdersWithPage(user,pm.getStartIndex(),pm.getPageSize());
		pm.setList(list);
		//关联URL
		pm.setUrl("OrderServlet?method=findOrdersWithPage");
		return pm;
	}

	@Override
	public Order findOrderByOid(String oid) throws Exception {
		// TODO Auto-generated method stub
		Order order=orderDao.findOrderByOid(oid);
		return order;
	}

	@Override
	public void updateOrder(Order order) throws Exception {
		// TODO Auto-generated method stub
		orderDao.updateOrder(order);
	}

	@Override
	public List<Order> findAllOrders() throws Exception {
		// TODO Auto-generated method stub
		return orderDao.findAllOrders();
	}

	@Override
	public List<Order> findAllOrders(String state) throws Exception {
		// TODO Auto-generated method stub
		return orderDao.findAllOrders(state);
	}

}
