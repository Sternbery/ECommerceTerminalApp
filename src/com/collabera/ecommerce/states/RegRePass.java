package com.collabera.ecommerce.states;

import com.collabera.ecommerce.ECommerceTerminalApp;
import com.collabera.ecommerce.dao.User;
import com.collabera.ecommerce.dao.UserDao;
import com.collabera.fsm.State;

public class RegRePass implements State {

	@Override
	public void act(String input) {
		ECommerceTerminalApp app = ECommerceTerminalApp.getSingleton();
		UserDao.addUser(new User(app.username,app.passwordHash));
	}

	@Override
	public String getOutput() {
		return "You have successfully logged in.";
	}

}
