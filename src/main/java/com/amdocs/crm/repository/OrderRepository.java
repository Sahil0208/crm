package com.amdocs.crm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.amdocs.crm.model.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
	
	public Order findByUserIdAndOrderId(long userId, long orderId);
	
	public List<Order> findByUserId(long userId);
	
	public Order findByOrderId(long orderId);
}
