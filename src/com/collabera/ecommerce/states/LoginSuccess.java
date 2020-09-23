package com.collabera.ecommerce.states;

import com.collabera.ecommerce.ECommerceTerminalApp;
import com.collabera.fsm.State;

public class LoginSuccess implements State{

	@Override
	public boolean check(String input) {
		String username = ECommerceTerminalApp.getSingleton().username;
		if("a".equals(username) && "a".equals(input))
			return true;
		return false;
	}

	@Override
	public void act(String input) {
		ECommerceTerminalApp.getSingleton().loggedin=true;
	}

	@Override
	public String getOutput() {
		return "{rb.login_success}";
	}

}
