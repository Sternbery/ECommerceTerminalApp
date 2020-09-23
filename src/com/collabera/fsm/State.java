package com.collabera.fsm;

public interface State {
	public void act(String input);
	public String getOutput();
	public default boolean isDefault() {
		return false;
	}
}
