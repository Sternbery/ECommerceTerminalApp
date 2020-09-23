package com.collabera.ecommerce.dao;

public class User {
	public int id;
	public String email;
	public String passwordHash;
	
	public User(int id, String email, String passwordHash) {
		this.id = id;
		this.email = email;
		this.passwordHash = passwordHash;
	}
}
