package com.ddh.store.web.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ddh.store.domain.User;
import com.ddh.store.service.UserService;
import com.ddh.store.service.serviceImp.UserServiceImp;
import com.ddh.store.utils.MailUtils;
import com.ddh.store.utils.MyBeanUtils;
import com.ddh.store.utils.UUIDUtils;
import com.ddh.store.web.base.BaseServlet;

/**
 * Servlet implementation class UserServlet
 */
public class UserServlet extends BaseServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public String registUI(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		return "/jsp/register.jsp";
	}
	public String loginUI(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		return "/jsp/login.jsp";
	}
	public String CheckUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        String name=request.getParameter("name");	
		//调用登录业务层功能
		UserService UserService=new UserServiceImp();
		boolean flag=UserService.findName(name);
		if(flag) {
			response.getWriter().write("1");
		}else {
			response.getWriter().write("0");
		}
		return null;
	}
	public String userRegist(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//接受表单参数
		Map<String, String[]> map=request.getParameterMap();
		User user=new User();
		MyBeanUtils.populate(user, map);//填充数据
		user.setUid(UUIDUtils.getId());//设置唯一id
		user.setState(0);//默认状态为0
		user.setCode(UUIDUtils.getId());//设置邮箱激活码
		UserService userService=new UserServiceImp();
		try {
			userService.userRegist(user);
			MailUtils.sendMail(user.getEmail(), user.getCode());//发送邮件激活码
			request.setAttribute("msg", "用户注册成功！请登录邮箱激活账户！");		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			request.setAttribute("msg", "用户注册失败,请重新注册！");
		}		
		return "/jsp/info.jsp";
	}
	public String active(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		// 获取激活码
		String code=request.getParameter("code");
		//调用业务层激活码功能
		UserService userService=new UserServiceImp();
		boolean flag=userService.userActive(code);
		if(flag=true) {
			//激活成功
			request.setAttribute("msg", "用户激活成功！");
			return "/jsp/login.jsp";
		}else {
			//激活失败
			request.setAttribute("msg", "用户激活失败请重新激活！");
			return "/jsp/info.jsp";
		}		
	}
	public String userLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user=new User();
		User UserDetail=null;
		//获取账户密码
		MyBeanUtils.populate(user,request.getParameterMap());//填充数据
		//调用登录业务层功能
		UserService UserService=new UserServiceImp();
		try {
			UserDetail=UserService.userLogin(user);//登录成功
			request.getSession().setAttribute("loginUser", UserDetail);
			if(UserDetail.getState()==10) {
				response.sendRedirect("/store/admin/home.jsp");
				return null;
			}
			response.sendRedirect("/store/index.jsp");
			return null;
		} catch (Exception e) {
			// 登录失败
			String msg=e.getMessage();
			request.setAttribute("msg", msg);
			return "/jsp/login.jsp";
		}
	}
	public String loginOut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 清除session
		request.getSession().invalidate();
		//重新定向到首页
		response.sendRedirect("/store/index.jsp");
		return null;
	}
}
