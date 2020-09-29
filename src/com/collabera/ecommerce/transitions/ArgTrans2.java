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
			return i;
		}
	}
	class ZeroOrMoreArg extends Arg{
		Arg arg;
		ZeroOrMoreArg(PatternArg arg){
			this.arg = arg;
		}
		int check(int pos, String[] tokens) {
			int i = 0;
			while(arg.check(pos+i, tokens)!=-1) {
				i++;
			}
			return i;
		}
	}
	
	int size=0;
	Arg[] patterns;
	public ArgTrans2(String... args) {
		patterns = new Arg[args.length];
		for(int i=0; i<args.length; i++) {
			if(args[i].equals("*")) {
				try {
					patterns[size-1]=new ZeroOrMoreArg((PatternArg)patterns[size-1]);
				}catch(ClassCastException e) {
					throw new IllegalArgumentException(e);
				}catch(NullPointerException e) {
					throw new IllegalArgumentException(e);
				}
				continue;
			}
			else if(args[i].equals("+")) {
				try {
					patterns[size-1]=new OneOrMoreArg((PatternArg)patterns[size-1]);
				}catch(ClassCastException e) {
					throw new IllegalArgumentException(e);
				}catch(NullPointerException e) {
					throw new IllegalArgumentException(e);
				}
				continue;
			}
			
			patterns[size]=new PatternArg(args[size]);
			size++;
		}
	}
	
	@Override
	public boolean checkAccepts(String input) {
		String[] tokens = input.split(" ");
		int j=0;
		for(int i=0;i<size; i++) {
			int q = patterns[i].check(j, tokens);
			j+=q;
			if(j>=tokens.length) //ran out of tokens before patterns
				return false;
		}
		return true;
	}

}
