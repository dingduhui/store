package com.ddh.store.service;

import java.util.List;

import com.ddh.store.domain.Order;
import com.ddh.store.domain.PageModel;
import com.ddh.store.domain.User;

public interface OrderService {

	void saveOrder(Order order) throws Exception;

	PageModel findOrdersWithPage(User user, int curNum)throws Exception;

	Order findOrderByOid(String oid)throws Exception;

	void updateOrder(Order order)throws Exception;

	List<Order> findAllOrders()throws Exception;

	List<Order> findAllOrders(String state)throws Exception;

}
