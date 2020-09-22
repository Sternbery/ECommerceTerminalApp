package com.collabera.ecommerce.states;

import com.collabera.fsm.State;

public class StartState implements State {

	@Override
	public boolean check(String input) {
		return "return".equals(input);
	}

	@Override
	public void act(String input) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String[] getOutput() {
		
		return new String[] {
				"register",
				"login",
				"exit",
		};
	}

}
