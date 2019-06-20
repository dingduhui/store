package com.ddh.store.web.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ddh.store.domain.Category;
import com.ddh.store.service.CategoryService;
import com.ddh.store.service.serviceImp.CategoryServiceImp;
import com.ddh.store.web.base.BaseServlet;

import net.sf.json.JSONArray;


/**
 * Servlet implementation class CategoryServlet
 */
public class CategoryServlet extends BaseServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public String findAllCats(HttpServletRequest request, HttpServletResponse response)throws Exception {
		// 调用业务层获取分类
		response.setContentType("application/json;charset=utf-8");
		CategoryService categoryService=new CategoryServiceImp();
		List<Category> list= categoryService.findAllCats();
		String jsonStr="";
	    jsonStr=JSONArray.fromObject(list).toString();
		//响应会客户端
		response.getWriter().print(jsonStr);
		return null;
	}
}
