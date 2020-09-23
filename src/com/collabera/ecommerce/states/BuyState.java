package com.collabera.ecommerce.states;

import com.collabera.fsm.State;

public class BuyState implements State{

	@Override
	public boolean check(String input) {
		return "buy".contentEquals(input);
	}

	@Override
	public void act(String input) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getOutput() {
		return "list\nadd\nreturn";
	}

}
