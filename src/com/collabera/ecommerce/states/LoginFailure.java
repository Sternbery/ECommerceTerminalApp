package com.collabera.ecommerce.states;

import com.collabera.ecommerce.ECommerceTerminalApp;
import com.collabera.fsm.State;

public class LoginFailure implements State{

	@Override
	public boolean check(String input) {
		return true;
	}

	@Override
	public void act(String input) {
		ECommerceTerminalApp.getSingleton().username=null;
	}

	@Override
	public String getOutput() {
		return "{rb.login_fail}\n{rb.enter_continue}";
	}
	
	@Override
	public boolean isDefault() {
		return true;
	}
}
