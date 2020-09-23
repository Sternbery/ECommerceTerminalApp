package com.collabera.ecommerce.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class RetailItemDao {
	private static int inc = 0;
	private static List<RetailItem> table = new ArrayList<>();
	static {
		table.add(new RetailItem(inc++,"Jacket","ja1",20));
		table.add(new RetailItem(inc++,"Jeans","je1",10));
		table.add(new RetailItem(inc++,"Shirt","sh1",5));
	}
	
	public static List<RetailItem> getRetailItemsFromTo(int index, int limit){
		return table.stream().skip(index).limit(limit).collect(Collectors.toList());
	}
	public static Optional<RetailItem> getByCode(String code){
		return table.parallelStream().filter(i->i.code.equals(code)).findAny();
	}
	public static Optional<RetailItem> getById(int id){
		return table
			.parallelStream()
			.filter(i->i.id==id)
			.findAny();
	}
}
