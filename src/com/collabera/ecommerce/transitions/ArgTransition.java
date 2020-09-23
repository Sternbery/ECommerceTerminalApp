package com.collabera.ecommerce.transitions;

import java.util.Arrays;
import java.util.regex.Pattern;

import com.collabera.fsm.Transition;

public class ArgTransition implements Transition {
	
	Pattern[] patterns;
	
	public static final String STR_REGEX = "\\S+";
	public static final String INT_REGEX = "\\d+";
	
	public ArgTransition(String...regexes) {
		patterns = new Pattern[regexes.length];
		for(int i=0; i<regexes.length; i++) {
			patterns[i] = Pattern.compile(regexes[i]);
		}
	}
	
	@Override
	public boolean checkAccepts(String input) {
		String[] args = input.split("\\s+");
		//System.out.println(Arrays.toString(args));
		
		if(args.length != patterns.length) {
			//System.out.println(patterns.length+" "+ args.length);
			return false;
		}
		for(int i=0; i<args.length; i++) {
			
			if(!patterns[i].matcher(args[i]).matches()) {
				//System.out.println("false");
				return false;
			}
			//System.out.println("true");
		}
		return true;
	}

}
