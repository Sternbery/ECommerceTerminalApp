package com.collabera.ecommerce.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserDao {
	
	private static int inc = 0;
	private static List<User> table = new ArrayList<>();
	static {
		table.add(new User(inc++,"a","a"));
	}
	
	public static boolean addUser(User u) {
		u.id = inc++;
		return table.add(u);
	}
	public static User getUserAtIndex(int index) {
		return table.get(index);
	}
	public static Optional<User> getUser(String e) {
		return table
			.stream()
			.filter(u->u.email.equals(e))
			.findFirst();
	}
	public static Optional<User> getUser(String e, String p) {
		return table
			.stream()
			.filter(u->u.email.equals(e) && u.passwordHash.equals(p))
			.findFirst();
	}
}
