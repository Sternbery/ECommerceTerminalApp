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
	
	private static ECommerceTerminalApp singleton;
	public String username;
	public boolean loggedin = false;
	
	public static ECommerceTerminalApp getSingleton() {
		if(singleton==null)
			singleton = new ECommerceTerminalApp();
		return singleton;
	}
	
	public void start() {
		State[] states = makeStates();
		List<Pair<Integer,Integer>> paths = this.getPaths();
		
		FiniteStateMachine fsm = new FiniteStateMachine(states, paths);
		
		ResourceBundle rb = getI18n();
		Scanner keyboard = new Scanner(System.in);
		
		while(!fsm.isInTerminalState()) {
			
			printStateOutput(fsm, rb);
			MatchResult mr;
			
			while( (mr = fsm.observe(keyboard.nextLine()))!=MatchResult.ONE) {
				
				if(mr == MatchResult.MANY) 
					System.out.println(rb.getString("ambiguous_input"));
				else if(mr == MatchResult.NONE) 
					System.out.println(rb.getString("invalid_input"));
			}
			System.out.println();
			
		}
		
		printStateOutput(fsm, rb);
		
		keyboard.close();
		System.out.println("Hello World!");
	}
	
	public ResourceBundle getI18n() {
		return ResourceBundle.getBundle("MenusBundle",new Locale("en","US"));
	}
	
	public State[] makeStates() {
		return new State[] {
				new StartState(),		//0
				new ExitState("exit"),	//1
				new BeginLogin(),		//2
				new EmailLogin(),		//3
				new LoginSuccess(),		//4
				new LoginFailure(),		//5
				new MenuState(),		//6
				new BuyState()			//7
			};
	}
	
	public List<Pair<Integer,Integer>> getPaths(){
		List<Pair<Integer,Integer>> paths = new ArrayList<>();
		//paths.add(new Pair<>(0,0));
		paths.add(new Pair<>(0,1));
		
		paths.add(new Pair<>(0,2));
		paths.add(new Pair<>(2,3));
		paths.add(new Pair<>(3,4));
		paths.add(new Pair<>(3,5));
		
		paths.add(new Pair<>(4,6));
		paths.add(new Pair<>(5,0));
		
		paths.add(new Pair<>(6,7));
		//paths.add(new Pair<>());
				
		return paths;
	}
	
	private void printStateOutput(FiniteStateMachine fsm, ResourceBundle rb) {
		System.out.println(fsm.getStateIndex());
		System.out.println(fsm.getState().getOutput());		
	}
	
	public static void main(String[] args) {
		getSingleton().start();
	}
}
