package com.collabera.ecommerce.states;

import com.collabera.fsm.State;

public class ListState implements State{

	private String response;
	
	@Override
	public boolean check(String input) {
		return input.substring(0, 4).contentEquals("list");
	}

	@Override
	public void act(String input) {
		if(input.contentEquals("list"))
			response = "useage: list <start_index> <number_of entries>";
	}

	@Override
	public String getOutput() {
		// TODO Auto-generated method stub
		return null;
	}

}
