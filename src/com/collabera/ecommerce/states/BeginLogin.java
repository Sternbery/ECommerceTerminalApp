package com.collabera.ecommerce.states;

import java.util.Scanner;

import com.collabera.fsm.State;

public class BeginLogin implements State{

	@Override
	public boolean check(String input) {
		return "login".contentEquals(input);
	}

	@Override
	public void act(String input) {
	}

	@Override
	public String getOutput() {
		return "{rb.enter_email}";
	}

}
