package com.collabera.ecommerce.dao;

public class RetailItem {
	public int id;
	public String name;
	public String code;
	public int price;
	
	public RetailItem(int id, String name, String code, int price) {
		super();
		this.id = id;
		this.name = name;
		this.code = code;
		this.price = price;
	}
	
	@Override
	public String toString() {
		return "RetailItem [id=" + id + ", name=" + name + ", code=" + code + ", price=" + price + "]";
	}
}
