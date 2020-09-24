package com.collabera.ecommerce.dao;

public class User {
	public int id;
	public String email;
	public String passwordHash;
	
	public User(String email, String passwordHash) {
		this.email = email;
		this.passwordHash = passwordHash;
	}
	public User(int id, String email, String passwordHash) {
		this.id = id;
		this.email = email;
		this.passwordHash = passwordHash;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", email=" + email + ", passwordHash=" + passwordHash + "]";
	}
}
