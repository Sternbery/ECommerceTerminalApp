package com.collabera.ecommerce.transitions;

public class NotOptionTransition extends OptionTransition {
	
	public NotOptionTransition(String... strings ) {
		super(strings);
	}
	
	@Override
	public boolean checkAccepts(String input) {
		return !super.checkAccepts(input);
	}
}
