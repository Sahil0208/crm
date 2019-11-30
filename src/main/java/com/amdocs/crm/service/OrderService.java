package com.amdocs.crm.service;

import java.util.List;
import java.util.Map;

import com.amdocs.crm.beans.CartSummary;
import com.amdocs.crm.model.Order;

public interface OrderService {

	public long createOrder(CartSummary cart);

	public List<Order> orders();

	public Map<String, Object> getOrder(Long orderId, Long userId);

	public List<Order> userOrders(long userId);

	public boolean cancelOrder(long orderId, long userId);

	public boolean rejectOrder(long orderId);

}
