package com.amdocs.crm.beans;

import java.util.List;

/**
 * The Class CartSummary.
 */
public class CartSummary {
	
	/** The cart items. */
	private List<ProductBean> cartItems;
	
	/** The shipping chares. */
	private double shippingChares;
	
	/** The grand total. */
	private double grandTotal;
	
	/** The tax. */
	private int tax;
	
	/** The total. */
	private double total;
	
	/** The tax amount. */
	private double taxAmount;
	
	/** The user id. */
	private long userId;

	/**
	 * Gets the cart items.
	 *
	 * @return the cart items
	 */
	public List<ProductBean> getCartItems() {
		return cartItems;
	}

	/**
	 * Gets the shipping chares.
	 *
	 * @return the shipping chares
	 */
	public double getShippingChares() {
		return shippingChares;
	}

	/**
	 * Gets the grand total.
	 *
	 * @return the grand total
	 */
	public double getGrandTotal() {
		return grandTotal;
	}

	/**
	 * Gets the tax.
	 *
	 * @return the tax
	 */
	public int getTax() {
		return tax;
	}

	/**
	 * Gets the total.
	 *
	 * @return the total
	 */
	public double getTotal() {
		return total;
	}

	/**
	 * Gets the tax amount.
	 *
	 * @return the tax amount
	 */
	public double getTaxAmount() {
		return taxAmount;
	}

	/**
	 * Gets the user id.
	 *
	 * @return the user id
	 */
	public long getUserId() {
		return userId;
	}

}
