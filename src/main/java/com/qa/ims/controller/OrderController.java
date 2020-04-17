package com.qa.ims.controller;

import java.util.List;

import org.apache.log4j.Logger;

import com.qa.ims.persistence.domain.Order;
import com.qa.ims.services.CrudServices;
import com.qa.ims.utils.Utils;

public class OrderController implements CrudController<Order>{
	
	public static final Logger LOGGER = Logger.getLogger(OrderController.class);

	private CrudServices<Order> orderService;
	
	public OrderController(CrudServices<Order> orderService) {
		this.orderService = orderService;
	}
	

	String getInput() {
		return Utils.getInput();
	}
	
	@Override
	public List<Order> readAll() {
		List<Order> orders = orderService.readAll();
		for(Order order: orders) {
			LOGGER.info(order.toString());
		}
		return orders;
	}

	@Override
	public Order create() {
		LOGGER.info("Please enter the customer id");
		Long customerid = Long.valueOf(getInput());
		LOGGER.info("Please enter tottal; price of order");
		Double tottalPrice = Double.valueOf(getInput());
		Order order = orderService.create(new Order(customerid, tottalPrice));
		LOGGER.info("Order Created");
		return order;
	}

	@Override
	public Order update() {
		LOGGER.info("Please enter the id of the order you would like to update");
		Long order_id = Long.valueOf(getInput());
		LOGGER.info("Please enter Customer ID");
		Long customerid = Long.valueOf(getInput());
		LOGGER.info("Please enter the Tottal Price");
		Double tottalPrice = Double.valueOf(getInput());
		Order order = orderService.update(new Order(order_id, customerid, tottalPrice));
		LOGGER.info("Order Updated");
		return order;
	}
	
	@Override
	public void delete() {
		LOGGER.info("Please enter the id of the order you would like to delete");
		Long order_id = Long.valueOf(getInput());
		orderService.delete(order_id);
	}
}