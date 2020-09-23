package com.collabera.ecommerce.transitions;

import com.collabera.ecommerce.ECommerceTerminalApp;
import com.collabera.fsm.Transition;

public class PasswordTransition implements Transition {

	@Override
	public boolean checkAccepts(String input) {
		return ECommerceTerminalApp.getSingleton().attemptLogin(input);
		
	}

}
