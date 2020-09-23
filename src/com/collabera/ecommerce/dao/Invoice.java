package com.collabera.ecommerce.dao;

import java.sql.Date;

public class Invoice {
	public int id;
	public int user_id;
	public Date date_created;
	public boolean finished;
	public Date date_finished;
	@Override
	public String toString() {
		return "Invoice [id=" + id + ", user_id=" + user_id + ", date_created=" + date_created + ", finished="
				+ finished + ", date_finished=" + date_finished + "]";
	}
}
