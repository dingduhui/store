package com.ddh.store.web.servlet;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ddh.store.domain.Category;
import com.ddh.store.service.CategoryService;
import com.ddh.store.service.serviceImp.CategoryServiceImp;
import com.ddh.store.utils.UUIDUtils;
import com.ddh.store.web.base.BaseServlet;

/**
 * Servlet implementation class AdminCategoryServlet
 */
public class AdminCategoryServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	public String findCategoryCats(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		CategoryService categoryService = new CategoryServiceImp();
		List<Category> list = categoryService.findAllCats();
		req.setAttribute("allCats", list);
		return "/admin/category/list.jsp";
	}
	public String addCategoryUI(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		return "/admin/category/add.jsp";
	}
	public String addCategory(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		//获取商品名称
		String cname=req.getParameter("cname");
		//设置商品UUID
		String uid=UUIDUtils.getCode();
		Category category=new Category();
		category.setCid(uid);
		category.setCname(cname);
		//调用业务层功能
		CategoryService categoryService=new CategoryServiceImp(); 
		categoryService.addCategory(category);
		//重新定向到查询所有商品界面
		resp.sendRedirect("/store/AdminCategoryServlet?method=findCategoryCats");
		return null;
	}
	public String deletCategory(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		//获取商品id
		String cid=req.getParameter("cid");	
		//调用业务层修改cid为null
		CategoryService categoryService = new CategoryServiceImp();
		categoryService.deletOneCat(cid);						
		resp.sendRedirect("/store/AdminCategoryServlet?method=findCategoryCats");
		return null;
	}
}
