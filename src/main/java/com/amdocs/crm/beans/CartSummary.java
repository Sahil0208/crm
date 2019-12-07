package com.amdocs.crm.beans;

import java.util.List;

public class CartSummary {

	private List<ProductBean> cartItems;

	private double shippingChares;

	private double grandTotal;

	private int tax;

	private double total;

	private double taxAmount;

	private long userId;

	public List<ProductBean> getCartItems() {
		return cartItems;
	}

	public void setCartItems(List<ProductBean> cartItems) {
		this.cartItems = cartItems;
	}

	public double getShippingChares() {
		return shippingChares;
	}

	public void setShippingChares(double shippingChares) {
		this.shippingChares = shippingChares;
	}

	public double getGrandTotal() {
		return grandTotal;
	}

	public void setGrandTotal(double grandTotal) {
		this.grandTotal = grandTotal;
	}

	public int getTax() {
		return tax;
	}

	public void setTax(int tax) {
		this.tax = tax;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public double getTaxAmount() {
		return taxAmount;
	}

	public void setTaxAmount(double taxAmount) {
		this.taxAmount = taxAmount;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

}
