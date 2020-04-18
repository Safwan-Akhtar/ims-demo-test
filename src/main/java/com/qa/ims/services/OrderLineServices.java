package com.qa.ims.services;

import java.util.List;

import com.qa.ims.persistence.dao.Dao;
import com.qa.ims.persistence.domain.OrderLine;

public class OrderLineServices implements CrudServices<OrderLine> {

	private Dao<OrderLine> orderlineDao;
	
	public OrderLineServices(Dao<OrderLine> orderlineDao) {
		this.orderlineDao = orderlineDao;
	}
	
	public List<OrderLine> readAll() {
		return orderlineDao.readAll();
	}

	public OrderLine create(OrderLine orderline) {
		return orderlineDao.create(orderline);
	}

	public OrderLine update(OrderLine orderline) {
		return orderlineDao.update(orderline);
	}

	public void delete(Long id) {
		orderlineDao.delete(id);
	}

}