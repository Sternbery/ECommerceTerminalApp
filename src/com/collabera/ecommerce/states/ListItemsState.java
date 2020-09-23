package com.collabera.ecommerce.states;

import com.collabera.fsm.State;

public class ListItemsState implements State {

	int index, length;
	
	@Override
	public void act(String input) {
		String[] options = input.split(" ");
		index = Integer.parseInt(options[1]);
		length = Integer.parseInt(options[2]);
		
	}

	@Override
	public String getOutput() {
		return "show items from " +index+" to "+(index+length);
	}

}
