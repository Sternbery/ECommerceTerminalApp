package com.collabera.ecommerce.states;

import com.collabera.fsm.State;

public class BeginRegState implements State {

	@Override
	public void act(String input) {
	}

	@Override
	public String getOutput() {
		return "Please enter the email address";
	}

}
