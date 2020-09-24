package com.collabera.ecommerce.states;

import com.collabera.fsm.State;

public class SimpleMessageState implements State {

	String message;
	
	public SimpleMessageState(String message) {
		this.message = message;
	}
	
	@Override
	public void act(String input) {
	}

	@Override
	public String getOutput() {
		return message;
	}

}
