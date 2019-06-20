package com.ddh.store.domain;

public class Cartltem {
private Product product;
private int num;//当前类别商品的数量
private double subTotal;

@Override
public String toString() {
	return "Cartltem [product=" + product + ", num=" + num + ", subTotal=" + subTotal + "]";
}
public Cartltem() {
	super();
	// TODO Auto-generated constructor stub
}
public Product getProduct() {
	return product;
}
public void setProduct(Product product) {
	this.product = product;
}
public int getNum() {
	return num;
}
public void setNum(int num) {
	this.num = num;
}
public double getSubTotal() {
	return Integer.parseInt(product.getShop_price())*num;
}
public void setSubTotal(double subTotal) {
	this.subTotal = subTotal;
}

}
