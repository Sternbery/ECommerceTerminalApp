package com.collabera.ecommerce.dao;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class InvoiceDao {
	private static int inc = 0;
	private static List<Invoice> table = new ArrayList<>();
	
	public static Optional<Invoice> getActiveInvoice(User u){
		if(u == null) return Optional.empty();
		return table.parallelStream()
			.filter(i->i.user_id == u.id)
			.filter(i->!i.finished)
			.findAny();
	}
	public static Invoice ensureActiveInvoice(User u){
		if(u == null) throw new IllegalArgumentException("null user argument");
		return table.parallelStream()
			.filter(i->i.user_id == u.id)
			.filter(i->!i.finished)
			.findAny()
			.orElseGet(()->{
				Invoice newInvo = new Invoice();
				newInvo.id = inc++;
				newInvo.user_id=u.id;
				newInvo.date_created = new Date(new java.util.Date().getTime());
				newInvo.finished = false;
				newInvo.date_finished = newInvo.date_created;
				table.add(newInvo);
				return newInvo;
			});
	}
	public static boolean updateInvoice(Invoice invo) {
		return table.parallelStream()
			.filter(i->i.id==invo.id)
			.findFirst()
			.map(i->{
				i.user_id = invo.user_id;
				i.date_created = invo.date_created;
				i.finished = invo.finished;
				i.date_finished = invo.date_finished;
				return true;
			})
			.orElse(false);
	}
	public static List<Invoice> retrieveByUse(User u){
		return table.parallelStream()
				.filter(i->i.user_id==u.id)
				.collect(Collectors.toList());
				
	}
}
