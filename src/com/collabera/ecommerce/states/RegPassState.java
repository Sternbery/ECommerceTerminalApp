package com.collabera.ecommerce.states;

import com.collabera.ecommerce.ECommerceTerminalApp;
import com.collabera.fsm.State;

public class RegPassState implements State{

	@Override
	public void act(String input) {
		ECommerceTerminalApp.getSingleton().passwordHash = input;
	}

	@Override
	public String getOutput() {
		return "Please re-enter your password";
	}

}
