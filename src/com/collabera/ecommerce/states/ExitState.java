package com.collabera.ecommerce.states;

import com.collabera.fsm.State;

public class ExitState implements State{
	@Override
	public void act(String input) {
		System.out.println("exiting");
	}

	@Override
	public String getOutput() {
		return "exited";
	}

}
