package com.collabera.ecommerce.transitions;

import java.io.PrintStream;

import com.collabera.fsm.Transition;

public class AnyTransition implements Transition {

	@Override
	public boolean checkAccepts(String input) {
		return true;
	}

}
