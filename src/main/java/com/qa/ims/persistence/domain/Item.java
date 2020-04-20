package com.qa.ims.persistence.domain;

public class Item {
	
	private Long product_id;
	private String product_name;
	private Double product_price;
	
	public Item(String name, Double price) {
		this.product_name = name;
		this.product_price = price;	
	}
	
	public Item(Long id, String name, Double price) {
		this.product_id = id;
		this.product_name = name;
		this.product_price = price;
	}

	public Long getId() {
		return product_id;
	}

	public void setId(Long id) {
		this.product_id = id;
	}

	public String getName() {
		return product_name;
	}

	public void setName(String name) {
		this.product_name = name;
	}

	public double getPrice() {
		return product_price;
	}

	public void setPrice(Double price) {
		this.product_price = price;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((product_id == null) ? 0 : product_id.hashCode());
		result = prime * result + ((product_name == null) ? 0 : product_name.hashCode());
		long temp;
		temp = Double.doubleToLongBits(product_price);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Item other = (Item) obj;
		if (product_id == null) {
			if (other.product_id != null)
				return false;
		} else if (!product_id.equals(other.product_id))
			return false;
		if (product_name == null) {
			if (other.product_name != null)
				return false;
		} else if (!product_name.equals(other.product_name))
			return false;
		if (Double.doubleToLongBits(product_price) != Double.doubleToLongBits(other.product_price))
			return false;
		return true;
	}
	
	public String toString() {
		return "id:" + product_id + " Product name:" + product_name + " Product price:" + product_price;
	}
	
}
