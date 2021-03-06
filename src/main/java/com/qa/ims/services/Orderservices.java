package com.qa.ims.services;

import java.util.List;

import com.qa.ims.persistence.dao.Dao;
import com.qa.ims.persistence.domain.Order;

public class Orderservices implements CrudServices<Order> {

	private Dao<Order> orderDao;
	
	public Orderservices(Dao<Order> orderDao) {
		this.orderDao = orderDao;
	}
	
	public List<Order> readAll() {
		return orderDao.readAll();
	}

	public Order create(Order order) {
		return orderDao.create(order);
	}

	public Order update(Order customer) {
		return orderDao.update(customer);
	}

	public void delete(Long id) {
		orderDao.delete(id);
	}

}