package com.collabera.ecommerce.transitions;

import java.util.regex.Pattern;

import com.collabera.fsm.Transition;

public class ArgTrans2 implements Transition {

	abstract class Arg{
		abstract int check(int pos, String[] tokens);
	}
	class PatternArg extends Arg{
		Pattern pattern;
		PatternArg(String regex){
			pattern = Pattern.compile(regex);
		}
		int check(int pos, String[] tokens) {
			if(pattern.matcher(tokens[pos]).matches())
				return 1;
			return -1;
		}
	}
	class OneOrMoreArg extends Arg{
		Arg arg;
		OneOrMoreArg(PatternArg arg){
			this.arg = arg;
		}
		int check(int pos, String[] tokens) {
			int i = 0;
			while(arg.check(pos+i, tokens)!=-1) {
				i++;
			}
			if(i==0) return -1;
			return 1;
		}
	}
	
	public ArgTrans2(String... args) {
		
	}
	
	@Override
	public boolean checkAccepts(String input) {
		return false;
	}

}
