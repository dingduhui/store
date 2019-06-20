package com.ddh.store.web.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ddh.store.domain.Order;
import com.ddh.store.service.OrderService;
import com.ddh.store.service.serviceImp.OrderServiceImp;
import com.ddh.store.web.base.BaseServlet;

import net.sf.json.JSONArray;

/**
 * Servlet implementation class AdminOrderServlet
 */
public class AdminOrderServlet extends BaseServlet{
	private static final long serialVersionUID = 1L;
	public String findOrders(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		String state=req.getParameter("state");
		OrderService orderService=new OrderServiceImp();
		List<Order> list=null;
		if(state==null) {
			//获取全部订单
			list= orderService.findAllOrders();
			req.setAttribute("allOrders", list);
			return "/admin/order/list.jsp";
		}
		//获取分类状态订单
		list= orderService.findAllOrders(state);		
		//存入request转发
		req.setAttribute("allOrders", list);
		return "/admin/order/list.jsp";
	}
	public String findOrderByOidWithAjax(HttpServletRequest req, HttpServletResponse resp) throws Exception {
	String id=req.getParameter("id");
	OrderService orderService=new OrderServiceImp();
	Order order=orderService.findOrderByOid(id);
	String jsonStr=JSONArray.fromObject(order.getList()).toString();	
	resp.setContentType("application/json;charset=utf-8");
	resp.getWriter().println(jsonStr);
	return null;
	}
	public String updateOrderByOid(HttpServletRequest req, HttpServletResponse resp) throws Exception {
	String oid=req.getParameter("oid");
	OrderService orderService=new OrderServiceImp();
	Order order=orderService.findOrderByOid(oid);
	order.setState(3);
	orderService.updateOrder(order);
	resp.sendRedirect("/store/AdminOrderServlet?method=findOrders&state=3");
	return null;
	}
}
