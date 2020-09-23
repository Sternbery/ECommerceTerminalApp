package com.collabera.ecommerce;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Scanner;

import com.collabera.ecommerce.dao.UserDao;
import com.collabera.ecommerce.states.*;
import com.collabera.ecommerce.transitions.*;
import com.collabera.fsm.FiniteStateMachine;
import com.collabera.fsm.MatchResult;
import com.collabera.fsm.State;
import com.collabera.fsm.Transition;
import com.collabera.tools.Pair;

public class ECommerceTerminalApp {
	
	private static ECommerceTerminalApp singleton;
	public String username;
	
	public static ECommerceTerminalApp getSingleton() {
		if(singleton==null)
			singleton = new ECommerceTerminalApp();
		return singleton;
	}
	
	public void start() {
		FiniteStateMachine fsm = new FiniteStateMachine(makeStates(), getPaths());
		
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
	
	public boolean attemptLogin(String password) {
		return UserDao.GetUser(this.username, password).isPresent();
	}
	
	public ResourceBundle getI18n() {
		return ResourceBundle.getBundle("MenusBundle",new Locale("en","US"));
	}
	
	public State[] makeStates() {
		return new State[] {
				new EntranceState(),	//0
				new ExitState(),		//1
				new BeginLoginState(),	//2
				new EmailLoginState(),	//3
				new MenuState(			//4
					"REPLACE an Item",
					"BUY an Item", 
					"LOGOUT"),
				new MenuState(			//5 BuyState
					"List <index> <length>",
					"Add <id>",
					"checkout",
					"RETURN to menu"),
				new ListItemsState()	//6
			};
	}
	
	public List<List<Pair<Transition,Integer>>> getPaths(){
		List<List<Pair<Transition,Integer>>> paths = new ArrayList<>();
		List<Pair<Transition,Integer>> path;

		//Entrance 0
		path = new ArrayList<>();
		path.add(new Pair<>(new OptionTransition("exit"),1));
		path.add(new Pair<>(new OptionTransition("login"),2));
		paths.add(path);
		
		//Exit 1
		path = new ArrayList<>();
		paths.add(path);
		
		//BeginLogin 2
		path = new ArrayList<>();
		path.add(new Pair<>(new AnyTransition(), 3));
		paths.add(path);
		
		//EmailLogin 3
		path = new ArrayList<>();
		path.add(new Pair<>(new PasswordTransition(), 4));
		path.add(new Pair<>(new PasswordFailTransition(), 0));
		paths.add(path);
		
		//MenuLogin 4
		path = new ArrayList<>();
		path.add(new Pair<>(new OptionTransition("logout"),0));
		path.add(new Pair<>(new OptionTransition("buy"),5));
		paths.add(path);
		
		//BuyState 5
		path = new ArrayList<>();
		path.add(new Pair<>(new OptionTransition("return"),4));
		path.add(new Pair<>(new OptionTransition("list"),5));
		path.add(new Pair<>(new ArgTransition("list","\\d+","\\d+"),6));
		paths.add(path);
		
		//ListItemsState 6
		path = new ArrayList<>();
		path.add(new Pair<>(new AnyTransition(),4));
		paths.add(path);
			
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
