package com.collabera.ecommerce.states;

import java.util.Arrays;

import com.collabera.fsm.State;

public class MenuState implements State {

	String options;
	
	public MenuState(String... myoptions) {
		this.options =Arrays.stream(myoptions).reduce("", (acc,next)->acc+next+"\n");
	}
	
	@Override
	public void act(String input) {
	}

	@Override
	public String getOutput() {
		return options;
	}

}
