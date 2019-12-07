package com.amdocs.crm.service;

import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.amdocs.crm.model.Order;
import com.amdocs.crm.model.User;
import com.amdocs.crm.repository.OrderRepository;
import com.sendgrid.Content;
import com.sendgrid.Email;
import com.sendgrid.Mail;
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;

@Service
public class EmailService {

	private static final String SENDGRID_API_KEY = "";

	@Autowired
	private TemplateEngine templateEngine;

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private UserService userService;

	private static final String ORDER_CREATION_TEMPLATE = "email-template.html";

	public void sendOrderCreationMail(long orderId) {
		Optional<Order> order = orderRepository.findById(orderId);
		if (order != null && order.isPresent()) {
			Order userOrder = order.get();
			User user = userService.getUser(userOrder.getUserId());
			Context context = getContext(userOrder, user);
			sendMail("Order created", user.getEmail(), ORDER_CREATION_TEMPLATE, context);
		}
	}

	private Context getContext(Order order, User user) {
		final Context ctx = new Context();
		ctx.setVariable("fullName", user.getFullName());
		//ctx.setVariable("orderItems", order.getOrderItems());
		ctx.setVariable("shippingChares", order.getShippingChares());
		ctx.setVariable("grandTotal", order.getGrandTotal());
		ctx.setVariable("taxPercentage", order.getTaxPercentage());
		ctx.setVariable("subTotal", order.getSubTotal());
		ctx.setVariable("taxAmount", order.getTaxAmount());
		return ctx;
	}

	private void sendMail(String subject, String toEmail, String templateName, Context context) {
		Email from = new Email("test@example.com");
		Email to = new Email(toEmail);
		Content content = new Content("text/html", templateEngine.process("email-template.html", context));
		Mail mail = new Mail(from, subject, to, content);
		SendGrid sendGrid = new SendGrid(SENDGRID_API_KEY);
		Request request = new Request();
		try {
			request.setMethod(Method.POST);
			request.setEndpoint("mail/send");
			request.setBody(mail.build());
			Response response = sendGrid.api(request);
			response.getStatusCode();
		} catch (IOException exception) {
			exception.printStackTrace();
		}
	}
}
