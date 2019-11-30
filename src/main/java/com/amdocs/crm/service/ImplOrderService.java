package com.amdocs.crm.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.amdocs.crm.beans.CartSummary;
import com.amdocs.crm.beans.ProductBean;
import com.amdocs.crm.model.Order;
import com.amdocs.crm.model.OrderItem;
import com.amdocs.crm.model.enums.OrderStatus;
import com.amdocs.crm.repository.OrderItemRepository;
import com.amdocs.crm.repository.OrderRepository;

@Service
public class ImplOrderService implements OrderService {

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private OrderItemRepository orderItemRepository;

	@Autowired
	private EmailService emailService;

	@Autowired
	private UserService orderService;

	@Override
	public long createOrder(CartSummary cart) {
		long orderId = 0;
		Order order = new Order();
		order.setStatus(OrderStatus.CREATED);
		order.setStatusDescription(OrderStatus.CREATED.getStatusDescription());
		order.setShippingChares(cart.getShippingChares());
		order.setGrandTotal(cart.getGrandTotal());
		order.setTaxAmount(cart.getTaxAmount());
		order.setTaxPercentage(cart.getTax());
		order.setSubTotal(cart.getTotal());
		order.setUserId(cart.getUserId());
		order = orderRepository.save(order);
		if (order.getOrderId() > 0) {
			for (ProductBean productBean : cart.getCartItems()) {
				OrderItem orderItem = new OrderItem();
				orderItem.setProductId(productBean.getProductId());
				orderItem.setProductName(productBean.getProductName());
				orderItem.setDescription(productBean.getDescription());
				orderItem.setImageUrl(productBean.getImageUrl());
				orderItem.setBrand(productBean.getBrand());
				orderItem.setPrice(productBean.getPrice());
				orderItem.setQuantity(productBean.getQuantity());
				orderItem.setOrderId(order.getOrderId());
				orderItemRepository.save(orderItem);
				orderId = order.getOrderId();
				emailService.sendOrderCreationMail(orderId);
			}
		}
		return orderId;
	}

	@Override
	public List<Order> orders() {
		return orderRepository.findAll(new Sort(Direction.DESC, "creationDate"));
	}

	@Override
	public Map<String, Object> getOrder(Long orderId, Long userId) {
		Map<String, Object> orderDetails = new HashMap<String, Object>();
		Order order = userId != null ? orderRepository.findByUserIdAndOrderId(userId, orderId)
				: orderRepository.findByOrderId(orderId);
		if (order != null) {
			orderDetails.put("order", order);
			orderDetails.put("user", orderService.getUser(order.getUserId()));
			return orderDetails;
		}
		return null;
	}

	@Override
	public List<Order> userOrders(long userId) {
		return orderRepository.findByUserId(userId);
	}

	@Override
	public boolean cancelOrder(long orderId, long userId) {
		Order order = orderRepository.findByUserIdAndOrderId(userId, orderId);
		if (order != null) {
			order.setStatus(OrderStatus.CANCELED);
			order.setStatusDescription(OrderStatus.CANCELED.getStatusDescription());
			order.setUpdationDate(new Date());
			orderRepository.save(order);
			return true;
		}
		return false;
	}

	@Override
	public boolean rejectOrder(long orderId) {
		Order order = orderRepository.findByOrderId(orderId);
		if (order != null) {
			order.setStatus(OrderStatus.REJECTED);
			order.setStatusDescription(OrderStatus.REJECTED.getStatusDescription());
			order.setUpdationDate(new Date());
			orderRepository.save(order);
			return true;
		}
		return false;
	}
}
