package com.collabera.ecommerce.states;

import java.util.Arrays;

import com.collabera.fsm.State;

public class MenuState implements State {

	String options;
	
	public MenuState(String... myoptions) {
		int padding = 20;
		StringBuilder sb = new StringBuilder();
		
		int max=0;
		for(int i=0;i<myoptions.length;i++){
			max = myoptions[i].length()>max?myoptions[i].length():max;
		}
		
		max+= padding-2;
		sb.append('+');
		for(int i=0;i<max-1;i++) {
			sb.append('=');
		} 
		sb.append("+\n");
		for(int i=0;i<myoptions.length;i++) {
			sb.append("| ");
			sb.append((i+1));
			sb.append(". ");
			sb.append(myoptions[i]);
			for(int j=myoptions[i].length()+5;j<max;j++)
				sb.append(' ');
			sb.append("|\n");
		}
		sb.append('+');
		for(int i=0;i<max-1;i++) {
			sb.append('=');
		} 
		sb.append("+\n");
		options = sb.toString();
	}
	
	@Override
	public void act(String input) {
	}

	@Override
	public String getOutput() {
		return options;
	}

}
