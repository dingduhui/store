package com.ddh.store.web.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.connector.Request;

import com.ddh.store.domain.PageModel;
import com.ddh.store.domain.Product;
import com.ddh.store.service.ProductService;
import com.ddh.store.service.serviceImp.ProductServiceImp;
import com.ddh.store.web.base.BaseServlet;

/**
 * Servlet implementation class ProductServlet
 */
public class ProductServlet extends BaseServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public String findProductByPid(HttpServletRequest Request, HttpServletResponse response) throws Exception {
		String pid=Request.getParameter("pid");//获取商品pid
		ProductService productService=new ProductServiceImp();
		Product product=productService.findProductBypid(pid);
		Request.setAttribute("product", product);
		return "/jsp/product_info.jsp";
	}
	public String findProductsByCidWithPage(HttpServletRequest Request, HttpServletResponse response) throws Exception {
		String cid=Request.getParameter("cid");
		int curNum=Integer.parseInt(Request.getParameter("num"));
		ProductService productService=new ProductServiceImp();
		PageModel pageModel=productService.findProductsByCidWithPage(cid,curNum);
		//将PageModel对象存入到request
		Request.setAttribute("page", pageModel);
		return "/jsp/product_list.jsp";
	}
}
