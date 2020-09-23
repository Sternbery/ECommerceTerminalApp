package com.collabera.ecommerce.states;

import com.collabera.fsm.State;

public class EntranceState implements State {

	@Override
	public void act(String input) {
	}

	@Override
	public String getOutput() {
		return 	"+=====================+\n"+
				"| 1. register         |\n"+
				"| 2. login            |\n"+
				"| 3. exit             |\n"+
				"+=====================+";
	}

}
