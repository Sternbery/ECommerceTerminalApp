package com.collabera.ecommerce.states;

import com.collabera.ecommerce.ECommerceTerminalApp;
import com.collabera.fsm.State;

public class EmailLoginState implements State {

	@Override
	public void act(String input) {
		ECommerceTerminalApp.getSingleton().username = input;
	}

	@Override
	public String getOutput() {
		return "please enter your password";
	}

}
