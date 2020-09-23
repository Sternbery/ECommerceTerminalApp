package com.collabera.ecommerce.states;

import com.collabera.ecommerce.ECommerceTerminalApp;
import com.collabera.fsm.State;

public class EmailLogin implements State{

	@Override
	public boolean check(String input) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public void act(String input) {
		ECommerceTerminalApp.getSingleton().username = input;
		
	}

	@Override
	public String getOutput() {
		return "{rb.enter_password}";
	}

	@Override
	public boolean isDefault() {
		return true;
	}
}
