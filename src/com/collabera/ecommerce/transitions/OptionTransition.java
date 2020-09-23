package com.collabera.ecommerce.transitions;

import com.collabera.fsm.Transition;

public class OptionTransition implements Transition{

	private final String[] options;
	
	public OptionTransition(String... strings ) {
		options = strings;
	}
	
	@Override
	public boolean checkAccepts(String input) {
		for(String option : options) {
			if(input.equals(option))
				return true;
		}
		return false;
	}

}
