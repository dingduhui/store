package com.ddh.store.web.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ddh.store.domain.Category;
import com.ddh.store.domain.Product;
import com.ddh.store.service.CategoryService;
import com.ddh.store.service.ProductService;
import com.ddh.store.service.serviceImp.CategoryServiceImp;
import com.ddh.store.service.serviceImp.ProductServiceImp;
import com.ddh.store.web.base.BaseServlet;

/**
 * Servlet implementation class IndexServlet
 */
public class IndexServlet extends BaseServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//查询最新商品，返回两个集合
		ProductService productService=new ProductServiceImp();
		List<Product> Hotslist=productService.findHots();
		List<Product> Newslist=productService.findNews();
		//转发到真实首页
		request.setAttribute("hots", Hotslist);
		request.setAttribute("news", Newslist);
		return "/jsp/index.jsp";
		
	}

}
