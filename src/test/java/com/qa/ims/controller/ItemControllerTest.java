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

import com.qa.ims.persistence.domain.Item;
import com.qa.ims.services.Itemservices;

@RunWith(MockitoJUnitRunner.class)
public class ItemControllerTest {

	@Mock
	private Itemservices itemServices;
	
	@Spy
	@InjectMocks
	private ItemController itemController;
	
	@Test
	public void readAllTest() {
		ItemController itemController = new ItemController(itemServices);
		List<Item> item = new ArrayList<>();
		item.add(new Item("FF7R", 60.00));
		item.add(new Item("FFXIV", 40.00));
		item.add(new Item("WoW", 20.00));
		Mockito.when(itemServices.readAll()).thenReturn(item);
		assertEquals(item, itemController.readAll());
	}
	
	@Test
	public void createTest() {
		String productName = "FF7R";
		String productPrice = "60.00";
		Mockito.doReturn(productName, productPrice).when(itemController).getInput();
		Item item = new Item(productName, Double.valueOf(Double.parseDouble((productPrice))));
		Item savedItem = new Item(1L, "FF7R", 60.00);
		Mockito.when(itemServices.create(item)).thenReturn(savedItem);
		assertEquals(savedItem, itemController.create());
	}
	
	@Test
	public void updateTest() {
		String productid = "1";
		String productName = "FF7R";
		String productPrice = "40.00";
		Mockito.doReturn(productid, productName, productPrice).when(itemController).getInput();
		Item item = new Item(Long.parseLong(productid), productName, Double.valueOf(Double.parseDouble(productPrice)));

//		Item item = new Item(1L, productName, productPrice);
		Mockito.when(itemServices.update(item)).thenReturn(item);
		assertEquals(item, itemController.update());
	}
	
	@Test
	public void deleteTest() {
		String productid = "1";
		Mockito.doReturn(productid).when(itemController).getInput();
		itemController.delete();
		Mockito.verify(itemServices, Mockito.times(1)).delete(1L);
	}
}
