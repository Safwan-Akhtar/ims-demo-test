package com.qa.ims.persistence.domain;

public class Item {
	
	private Long productid;
	private String productname;
	private Double price;
	
	public Item(String name, Double price) {
		this.productname = name;
		this.price = price;	
	}
	
	public Item(Long id, String name, Double price) {
		this.productid = id;
		this.productname = name;
		this.price = price;
	}

	public Long getId() {
		return productid;
	}

	public void setId(Long id) {
		this.productid = id;
	}

	public String getName() {
		return productname;
	}

	public void setName(String name) {
		this.productname = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((productid == null) ? 0 : productid.hashCode());
		result = prime * result + ((productname == null) ? 0 : productname.hashCode());
		long temp;
		temp = Double.doubleToLongBits(price);
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
		if (productid == null) {
			if (other.productid != null)
				return false;
		} else if (!productid.equals(other.productid))
			return false;
		if (productname == null) {
			if (other.productname != null)
				return false;
		} else if (!productname.equals(other.productname))
			return false;
		if (Double.doubleToLongBits(price) != Double.doubleToLongBits(other.price))
			return false;
		return true;
	}
	
	public String toString() {
		return "id:" + productid + " Product name:" + productname + " Product price:" + price;
	}
	
}
