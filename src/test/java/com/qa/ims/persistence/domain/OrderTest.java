package com.qa.ims.persistence.domain;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class OrderTest {
	
	private Order order;
	private Order other;
	
	@Before
	public void setup() {
		order = new Order(1L, 1L, Double.valueOf(40.00));
		other = new Order(1L, 1L, Double.valueOf(40.00));
		
	}
	
	@Test
	public void setterTest() {
		assertNotNull(order.getOrder_id());
		assertNotNull(order.getCust_id());
		assertNotNull(order.getTotalPrice());
		
		order.setOrder_id(null);
		assertNull(order.getOrder_id());
		order.setCust_id(null);
		assertNull(order.getCust_id());
		order.setTotalPrice(null);
		assertNull(order.getTotalPrice());
		
	}
	
	@Test
	public void equalsWithNull() {
		assertFalse(order.equals(null));
	}
	
	@Test
	public void equalsWithDifferentObject() {
		assertFalse(order.equals(new Object()));
	}
	
	@Test
	public void createOrderWithId() {
		assertEquals(1L, order.getOrder_id(), 0);
		assertEquals(1L, order.getCust_id(), 0);
		assertEquals(40.00, order.getTotalPrice(), 0);
	}
	
	@Test
	public void checkEquality() {
		assertTrue(order.equals(order));
	}
	
	@Test
	public void checkEqualityBetweenDifferentObjects() {
		assertTrue(order.equals(other));
	}
	
	@Test
	public void orderIdNullButOtherIdNotNull() {
		order.setOrder_id(null);
		assertFalse(order.equals(other));
	}
	
	@Test
	public void customerIdNotEqual() {
		other.setCust_id(2L);
		assertFalse(order.equals(other));
	}
	
	@Test
	public void checkEqualityBetweenDifferentObjectsNullOrderId() {
		order.setOrder_id(null);
		other.setOrder_id(null);
		assertTrue(order.equals(other));
	}
	
	@Test
	public void nullPrice() {
		order.setTotalPrice(null);
		assertFalse(order.equals(other));
	}
	
	@Test
	public void nullIdOnBoth() {
		order.setTotalPrice(null);
		other.setTotalPrice(null);
		assertTrue(order.equals(other));
	}
	
	@Test
	public void otherPriceDifferent() {
		other.setTotalPrice(20.00);
		assertFalse(order.equals(other));
	}
	
	@Test
	public void nullCustomerId() {
		order.setCust_id(null);
		assertFalse(order.equals(other));
	}
	
	@Test
	public void nullCustomerIdOnBoth() {
		order.setCust_id(null);
		other.setCust_id(null);
		assertTrue(order.equals(other));
	}
	
	@Test
	public void otherOrderIdDifferent() {
		other.setCust_id(2L);
		assertFalse(order.equals(other));
	}
	
	@Test
	public void constructorWithoutOrderId() {
		Order order = new Order(1L, 40.00);
		assertNull(order.getOrder_id());
		assertNotNull(order.getCust_id());
		assertNotNull(order.getTotalPrice());
	}
	
	@Test
	public void hashCodeTest() {
		assertEquals(order.hashCode(), other.hashCode());
	}
	@Test
	public void hashCodeTestWithNull() {
		Order order = new Order(null, null);
		Order other = new Order(null, null);
		assertEquals(order.hashCode(), other.hashCode());
	}
	
	@Test
	public void toStringTest() {
		String toString = "order_id:1 cust_id:1 totalPrice:40.0";
		assertEquals(toString, order.toString());
	}
}
