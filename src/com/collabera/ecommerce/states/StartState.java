package com.collabera.ecommerce.states;

import com.collabera.fsm.State;

public class StartState implements State {

	@Override
	public boolean check(String input) {
		return true;
	}

	@Override
	public void act(String input) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getOutput() {
		
		return 	"+=========================+\n"+
				"{rb.register}  |\n"+
				"{rb.login}  |\n"+
				"{rb.exit}  |\n"+
				"+=========================+";
		
	}
	
	@Override
	public boolean isDefault() {
		return true;
	}

}
