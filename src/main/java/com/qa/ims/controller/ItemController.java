package com.qa.ims.controller;

import java.util.List;

import org.apache.log4j.Logger;

import com.qa.ims.persistence.domain.Item;
import com.qa.ims.services.CrudServices;
import com.qa.ims.utils.Utils;

public class ItemController implements CrudController<Item>{
	
	public static final Logger LOGGER = Logger.getLogger(ItemController.class);
	
	private CrudServices<Item> itemService;
	
	public ItemController(CrudServices<Item> itemService) {
		this.itemService = itemService;
	}
	
	String getInput() {
		return Utils.getInput();
	}
	
	Double getPrice() {
		return Utils.getPrice();
	}

	@Override
	public List<Item> readAll() {
		List<Item> items = itemService.readAll();
		for(Item item: items) {
			LOGGER.info(item.toString());
		}
		return items;
	}
	
	@Override
	public Item create() {
		LOGGER.info("Please enter product name");
		String productName = getInput();
		LOGGER.info("Please enter a price");
		Double price = getPrice();
		Item item = itemService.create(new Item(productName, price));
		LOGGER.info("Product created");
		return item;
	}

	@Override
	public Item update() {
		LOGGER.info("Please enter the id of the product you would like to update");
		Long id = Long.valueOf(getInput());
		LOGGER.info("Please enter prduct name");
		String productName = getInput();
		LOGGER.info("Please enter a surname");
		Double price = getPrice();
		Item item = itemService.update(new Item(id, productName, price));
		LOGGER.info("Customer Updated");
		return item;
	}
	@Override
	public void delete() {
		LOGGER.info("Please enter the id of the product you would like to delete");
		Long id = Long.valueOf(getInput());
		itemService.delete(id);
	}
	

}
