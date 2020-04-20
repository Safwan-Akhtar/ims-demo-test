package com.qa.ims.controller;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import com.qa.ims.persistence.domain.Order;
import com.qa.ims.services.Orderservices;

@RunWith(MockitoJUnitRunner.class)
public class OrderControllerTest {
	
	@Mock
	private Orderservices orderServices;
	
	@Spy
	@InjectMocks
	private OrderController orderController;

	@Test
	public void readAllTest() {
		OrderController orderController = new OrderController(orderServices);
		List<Order> order = new ArrayList<>();
		order.add(new Order(1L, 60.00));
		order.add(new Order(2L, 40.00));
		order.add(new Order(3L, 20.00));
		Mockito.when(orderServices.readAll()).thenReturn(order);
		assertEquals(order, orderController.readAll());
	}
	
	@Test
	public void createTest() {
		String customerid = "1";
		String orderprice = "60.00";
		Mockito.doReturn(customerid, orderprice).when(orderController).getInput();
		Order order = new Order(Long.parseLong(customerid), Double.valueOf(Double.parseDouble((orderprice))));
		Order savedOrder = new Order(1L, 1L, 60.00);
		Mockito.when(orderServices.create(order)).thenReturn(savedOrder);
		assertEquals(savedOrder, orderController.create());
	}
	
	@Test
	public void updateTest() {
		String orderid = "1";
		String customerid = "1";
		String Price = "60.00";
		Mockito.doReturn(orderid, customerid, Price).when(orderController).getInput();
		Order order = new Order(Long.parseLong(orderid), Long.parseLong(customerid), Double.valueOf(Double.parseDouble(Price)));
		Mockito.when(orderServices.update(order)).thenReturn(order);
		assertEquals(order, orderController.update());
	}

	@Test
	public void deleteTest() {
		String orderid = "1";
		Mockito.doReturn(orderid).when(orderController).getInput();
		orderController.delete();
		Mockito.verify(orderServices, Mockito.times(1)).delete(1L);
	}
}
