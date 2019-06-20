package com.ddh.store.web.servlet;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ddh.store.domain.Cart;
import com.ddh.store.domain.Cartltem;
import com.ddh.store.domain.Product;
import com.ddh.store.service.ProductService;
import com.ddh.store.service.serviceImp.ProductServiceImp;
import com.ddh.store.web.base.BaseServlet;

public class CartServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	public String addCartItemToCart(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Cart cart = (Cart) request.getSession().getAttribute("cart");
		if (cart == null) {
			cart = new Cart();
			request.getSession().setAttribute("cart", cart);
		}
		String pid = request.getParameter("pid");
		int num = Integer.parseInt(request.getParameter("quantity"));
		// 通过id查商品对象
		ProductService productService = new ProductServiceImp();
		Product product = productService.findProductBypid(pid);
		// 获取待购买的购物项
		Cartltem cartltem = new Cartltem();
		cartltem.setNum(num);
		cartltem.setProduct(product);
		cart.addCart(cartltem);
		response.sendRedirect("/store/jsp/cart.jsp");
		return null;
	}
	public String removeCartItem(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String pid=request.getParameter("id");
		Cart cart=(Cart) request.getSession().getAttribute("cart");
		cart.delCart(pid);
		response.sendRedirect("/store/jsp/cart.jsp");
		return null;
	}
	public String clearCart(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Cart cart=(Cart) request.getSession().getAttribute("cart");
		cart.clearCart();
		response.sendRedirect("/store/jsp/cart.jsp");
		return null;
	}
}
