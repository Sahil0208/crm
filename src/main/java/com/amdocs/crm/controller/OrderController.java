package com.amdocs.crm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.amdocs.crm.beans.CartSummary;
import com.amdocs.crm.constants.CrmConstans;
import com.amdocs.crm.service.OrderService;
import com.amdocs.crm.utils.ResponseHandler;

@RestController
@RequestMapping("/crm")
public class OrderController {

	@Autowired
	private OrderService orderService;

	@PostMapping("/createOrder")
	public ResponseEntity<Object> createOrder(@RequestBody CartSummary cart) {
		if (cart == null) {
			return ResponseHandler.doErrorResponse(CrmConstans.CART_CANT_BE_NULL, HttpStatus.OK);
		}
		long orderId = orderService.createOrder(cart);
		if (orderId <= 0) {
			return ResponseHandler.doErrorResponse(CrmConstans.FAILED_TO_CREATE_ORDER, HttpStatus.OK);
		} else {
			return ResponseHandler.doSuccessResponse(orderId, HttpStatus.OK);
		}
	}

	@GetMapping("/orders")
	public ResponseEntity<Object> orders() {
		return ResponseHandler.doSuccessResponse(orderService.orders(), HttpStatus.OK);
	}

	@GetMapping("/order")
	public ResponseEntity<Object> getOrder(@RequestParam long orderId, @RequestParam long userId) {
		if (orderId < 1) {
			return ResponseHandler.doErrorResponse(CrmConstans.INVALID_ORDER_ID, HttpStatus.OK);
		}
		if (userId < 1) {
			return ResponseHandler.doErrorResponse(CrmConstans.INVALID_USER_ID, HttpStatus.OK);
		}
		return ResponseHandler.doSuccessResponse(orderService.orders(), HttpStatus.OK);
	}

	@GetMapping("/myOrders")
	public ResponseEntity<Object> getOrder(@RequestParam long userId) {
		if (userId < 1) {
			return ResponseHandler.doErrorResponse(CrmConstans.INVALID_USER_ID, HttpStatus.OK);
		}
		return ResponseHandler.doSuccessResponse(orderService.userOrders(userId), HttpStatus.OK);
	}

	@GetMapping("/cancelOrder")
	public ResponseEntity<Object> cancelOrder(@RequestParam long orderId, @RequestParam long userId) {
		if (userId < 1) {
			return ResponseHandler.doErrorResponse(CrmConstans.INVALID_USER_ID, HttpStatus.OK);
		}
		if (orderId < 1) {
			return ResponseHandler.doErrorResponse(CrmConstans.INVALID_ORDER_ID, HttpStatus.OK);
		}
		return ResponseHandler.doSuccessResponse(orderService.cancelOrder(orderId, userId), HttpStatus.OK);
	}

	@GetMapping("/order/rejectOrder")
	public ResponseEntity<Object> rejectOrder(@RequestParam long orderId) {
		if (orderId < 1) {
			return ResponseHandler.doErrorResponse(CrmConstans.INVALID_ORDER_ID, HttpStatus.OK);
		}
		return ResponseHandler.doSuccessResponse(orderService.rejectOrder(orderId), HttpStatus.OK);
	}

	@GetMapping("/order/orderDetails")
	public ResponseEntity<Object> getOrderDetails(@RequestParam Long orderId,
			@RequestParam(required = false) Long userId) {
		if (orderId == null || orderId < 1) {
			return ResponseHandler.doErrorResponse(CrmConstans.INVALID_ORDER_ID, HttpStatus.OK);
		}
		return ResponseHandler.doSuccessResponse(orderService.getOrder(orderId, userId), HttpStatus.OK);
	}

}
