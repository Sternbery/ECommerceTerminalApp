package com.collabera.ecommerce.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserDao {
	
	private static List<User> table = new ArrayList<>();
	static {
		table.add(new User(0,"a","a"));
	}
	
	public static boolean AddUser(User u) {
		return table.add(u);
	}
	
	public static Optional<User> GetUser(String e, String p) {
		return table
			.stream()
			.filter(u->u.email == e && u.passwordHash==p)
			.findFirst();
	}
}
