package com.collabera.ecommerce;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Scanner;

import com.collabera.ecommerce.states.*;
import com.collabera.fsm.FiniteStateMachine;
import com.collabera.fsm.MatchResult;
import com.collabera.fsm.State;
import com.collabera.tools.Pair;

public class ECommerceTerminalApp {
	
	
	
	public static ResourceBundle getI18n() {
		return ResourceBundle.getBundle("MenusBundle",new Locale("en","US"));
	}
	
	public static State[] makeStates() {
		return new State[] {
				new StartState(),
				new ExitState("exit")
			};
	}
	
	public static void main(String[] args) {
		State[] states = makeStates();
		List<Pair<Integer,Integer>> paths = new ArrayList<>();
		paths.add(new Pair<>(0,1));
		paths.add(new Pair<>(0,0));
		
		FiniteStateMachine fsm = new FiniteStateMachine(states, paths);
		
		ResourceBundle rb = getI18n();
		Scanner keyboard = new Scanner(System.in);
		
		while(!fsm.isInTerminalState()) {
			int i=1;
			for(String option : fsm.getState().getOutput()) {
				System.out.println( i + " " + rb.getString(option));
				i++;
			}
			MatchResult mr;
			while( (mr = fsm.observe(keyboard.nextLine()))!=MatchResult.ONE) {
				if(mr == MatchResult.MANY) 
					System.out.println(rb.getString("ambiguous_input"));
				else if(mr == MatchResult.NONE) 
					System.out.println(rb.getString("invalid_input"));
			}
			System.out.println("\n");
			
		}
		
		keyboard.close();
		System.out.println("Hello World!");
	}
}
