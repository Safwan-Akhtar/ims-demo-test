package com.qa.ims.controller;

import java.util.List;

import org.apache.log4j.Logger;

import com.qa.ims.persistence.domain.OrderLine;
import com.qa.ims.services.CrudServices;
import com.qa.ims.utils.Utils;

public class OrderLineController implements CrudController<OrderLine> {
	
	public static final Logger LOGGER = Logger.getLogger(CustomerController.class);
	
	private CrudServices<OrderLine> orderlineService;
	
	public OrderLineController(CrudServices<OrderLine> orderlineService) {
		this.orderlineService = orderlineService;
	}

	String getInput() {
		return Utils.getInput();
	}

	@Override
	public List<OrderLine> readAll() {
		List<OrderLine> orderline = orderlineService.readAll();
		for(OrderLine orderline1: orderline) {
			LOGGER.info(orderline1.toString());
		}
		return orderline;
	}
	@Override
	public OrderLine create() {
		LOGGER.info("Please enter an order id");
		Long orderid = Long.valueOf(getInput());
		LOGGER.info("Please enter an item id");
		Long itemid = Long.valueOf(getInput());
		OrderLine orderline = orderlineService.create(new OrderLine(orderid, itemid));
		LOGGER.info("OrderLine created");
		return orderline;
	}
	@Override
	public OrderLine update() {
		LOGGER.info("Please enter the Order id of the Orderline you would like to update");
		Long orderid = Long.valueOf(getInput());
		LOGGER.info("Please enter a first name");
		Long itemid = Long.valueOf(getInput());
		OrderLine orderline = orderlineService.update(new OrderLine(orderid, itemid));
		LOGGER.info("Customer Updated");
		return orderline;
	}
	@Override
	public void delete() {
		LOGGER.info("Please enter the order id of the orderline you would like to delete");
		Long orderId  = Long.valueOf(getInput());
		orderlineService.delete(orderId);
	}
		
}
