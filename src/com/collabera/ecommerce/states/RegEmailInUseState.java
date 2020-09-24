package com.collabera.ecommerce.states;

import com.collabera.fsm.State;

public class RegEmailInUseState implements State {

	@Override
	public void act(String input) {}
	
	@Override
	public String getOutput() {
		return "Sorry. That email is in use";
	}

}
