package com.collabera.ecommerce.states;

import com.collabera.fsm.State;

public class RegPassFailState implements State {

	@Override
	public void act(String input) {

	}

	@Override
	public String getOutput() {
		return "Passwords do not match. Try again";
	}

}
