package com.ddh.store.domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class Cart {
	@Override
	public String toString() {
		return "Cart [map=" + map + ", total=" + total + "]";
	}

	// 代表购物车上的个数不确定的购物项,键:商品pid,值:购物项
	private Map<String, Cartltem> map = new HashMap<String, Cartltem>();
	private double total = 0;

	// 1_向购物车中添加购物项
	public void addCart(Cartltem item) {
		// 获取到待添加到购物车中商品id
		String pid = item.getProduct().getPid();
		if (map.containsKey(pid)) {
			Cartltem old = map.get(pid);
			old.setNum(old.getNum() + item.getNum());
		} else {
			map.put(pid, item);
		}
	}

	// 2_从购物车中移除单个购物项
	public void delCart(String pid) {
		map.remove(pid);
		// total=total-map.get(pid).getSubTotal();
	}

	// 3_清空购物车
	public void clearCart() {
		map.clear();
		// total=0;
	}

	// 计算总计
	public double getTotal() {
		total = 0;
		for (Cartltem item : map.values()) {
			total = total + item.getSubTotal();
		}
		return total;
	}

	// 为了方便遍历MAP中的所有的购物项,提供以下方法
	public Collection<Cartltem> getCartItems() {
		return map.values();
	}

	public Map<String, Cartltem> getMap() {
		return map;
	}

	public void setMap(Map<String, Cartltem> map) {
		this.map = map;
	}

	public void setTotal(double total) {
		this.total = total;
	}

}
