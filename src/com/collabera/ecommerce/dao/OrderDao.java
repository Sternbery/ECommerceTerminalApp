package com.collabera.ecommerce.dao;

import java.util.List;
import java.util.stream.Collectors;
import java.util.ArrayList;

public class OrderDao {
	private static int inc = 0;
	private static List<Order> table = new ArrayList<>();
	
	public static boolean addOrder(Order order) {
		order.id = inc++;
		return table.add(order);
	}
	public static List<Order> retrieveByInvoice(Invoice i) {
		return table.parallelStream()
				.filter(o->o.invoice_id==i.id)
				.collect(Collectors.toList());
	}
	public static List<RetailItem> retrieveByInvoiceJoinItem(Invoice i) {
		return table.parallelStream()
				.filter(o->o.invoice_id==i.id)
				.map(o->RetailItemDao.getById(o.item_id))
				.filter(ori->ori.isPresent())
				.map(ori->ori.get())
				.collect(Collectors.toList());
	}
}
