package com.collabera.ecommerce.transitions;

import com.collabera.ecommerce.ECommerceTerminalApp;
import com.collabera.fsm.Transition;

public class PasswordFailTransition extends PasswordTransition {

	@Override
	public boolean checkAccepts(String input) {
		return !super.checkAccepts(input);
	}

}
