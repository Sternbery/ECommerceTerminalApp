package com.collabera.ecommerce.states;

import com.collabera.fsm.State;

public class ExitState implements State{

	public final String whatImLookingFor;
	
	public ExitState() {
		this.whatImLookingFor = null;
	}
	public ExitState(String whatImLookingFor) {
		this.whatImLookingFor = whatImLookingFor;
	}
	
	@Override
	public boolean check(String input) {
		if(this.whatImLookingFor == null)
			return true;
		return this.whatImLookingFor.equals(input);
	}

	@Override
	public void act(String input) {
		System.out.println("Entering Exit State");
	}

	@Override
	public String[] getOutput() {
		return new String[] {"exit_state"};
	}

}
