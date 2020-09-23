package com.collabera.fsm;


public interface Transition {
	public boolean checkAccepts(String input);
}
