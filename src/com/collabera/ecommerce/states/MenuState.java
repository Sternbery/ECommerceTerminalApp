package com.collabera.ecommerce.states;

import com.collabera.ecommerce.ECommerceTerminalApp;
import com.collabera.fsm.State;

public class MenuState implements State{

	@Override
	public boolean check(String input) {
		return "".equals(input) || "return".equals(input);
	}

	@Override
	public void act(String input) {
		ECommerceTerminalApp.getSingleton().loggedin=true;
	}

	@Override
	public String getOutput() {
		return 	"logged in as "+ECommerceTerminalApp.getSingleton().username+"\n"+
				"+=====================================+\n"+
				"{rb.buy_item}  |\n"+
				"{rb.replace_item}  |\n"+
				"{rb.logout}  |\n"+
				"+====================================+";
	}

	@Override
	public boolean isDefault() {
		return true;
	}
}
