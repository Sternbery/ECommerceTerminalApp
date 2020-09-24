package com.collabera.ecommerce;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.Scanner;

import com.collabera.ecommerce.dao.User;
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
	public String passwordHash;
	public User user;
	
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
		Optional<User> optionalUser = UserDao.getUser(this.username, password);
		if(optionalUser.isPresent()) {
			user = optionalUser.get();
			return true;
		}
		user = null;
		return false;
	}
	
	public ResourceBundle getI18n() {
		return ResourceBundle.getBundle("MenusBundle",new Locale("en","US"));
	}
	
	public State[] makeStates() {
		return new State[] {
				new MenuState(			//0
					"REGISTER",
					"LOGIN",
					"EXIT"),	
				new ExitState(),		//1
				new BeginLoginState(),	//2
				new EmailLoginState(),	//3
				new MenuState(			//4
					"REPLACE an Item",
					"BUY an Item", 
					"LIST <index> <length> your invoices",
					"LOGOUT"),
				new MenuState(			//5 BuyState
					"LIST <index> <length>",
					"ADD <id>",
					"CHECKOUT",
					"RETURN to menu"),
				new ListItemsState(),	//6
				new AddItemState(),		//7
				new CheckoutState(),	//8
				new ListInvoicesState(),//9
				new BeginRegState(),	//10
				new SimpleMessageState( //11
					"Sorry that email is already in use."),
				new SimpleMessageState( //11
					"Sorry, that email is invalid. Please try again."),
				new RegEmailSuccState(),//13
				new RegPassState(),		//14
				new RegRePass(),		//15
				new RegPassFailState()	//16
			};
	}
	
	public List<List<Pair<Transition,Integer>>> getPaths(){
		List<List<Pair<Transition,Integer>>> paths = new ArrayList<>();
		List<Pair<Transition,Integer>> path;

		//Entrance 0
		path = new ArrayList<>();
		path.add(new Pair<>(new OptionTransition("exit"),1));
		path.add(new Pair<>(new OptionTransition("login"),2));
		path.add(new Pair<>(new OptionTransition("register"),10));
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
		path.add(new Pair<>(new ArgTransition("list","\\d+","\\d+"),9));
		paths.add(path);
		
		//BuyState 5
		path = new ArrayList<>();
		path.add(new Pair<>(new OptionTransition("return"),4));
		path.add(new Pair<>(new ArgTransition("list","\\d+","\\d+"),6));
		path.add(new Pair<>(new ArgTransition("add","\\w+"),7));
		path.add(new Pair<>(new OptionTransition("checkout"),8));
		paths.add(path);
		
		//ListItemsState 6
		path = new ArrayList<>();
		path.add(new Pair<>(new AnyTransition(),5));
		paths.add(path);
		
		//AddItemState 7
		path = new ArrayList<>();
		path.add(new Pair<>(new AnyTransition(),5));
		paths.add(path);
		
		//CheckoutState 8
		path = new ArrayList<>();
		path.add(new Pair<>(new AnyTransition(),5));
		paths.add(path);
		
		//ListInvoicesState 9 
		path = new ArrayList<>();
		path.add(new Pair<>(new AnyTransition(),4));
		paths.add(path);
		
		//BeginRegState 10 
		path = new ArrayList<>();
		final String ig = "cancel";
		path.add(new Pair<>(new OptionTransition(ig),0));
		path.add(new Pair<>(RegisterTransitions.getCheckEmailInUseTransition(ig),11));
		path.add(new Pair<>(RegisterTransitions.getCheckEmailInvalidTransition(ig),12));
		path.add(new Pair<>(RegisterTransitions.getEmailGoodTransition(ig),13));
		paths.add(path);
		
		//RegEmailInUseState 11
		path = new ArrayList<>();
		path.add(new Pair<>(new AnyTransition(),10));
		paths.add(path);
		
		//RegEmailBadState 12
		path = new ArrayList<>();
		path.add(new Pair<>(new AnyTransition(),10));
		paths.add(path);
		
		//RegEmailSuccState 13
		path = new ArrayList<>();
		path.add(new Pair<>(new OptionTransition(ig),0));
		path.add(new Pair<>(new NotOptionTransition(ig),14));
		paths.add(path);
		
		//RegPassState 14
		path = new ArrayList<>();
		path.add(new Pair<>(RegisterTransitions.getReEnterPasswordTransition() ,15));
		path.add(new Pair<>(RegisterTransitions.getReEnterPasswordailTransition() ,16));
		paths.add(path);
		
		//RegRePassState 15
		path = new ArrayList<>();
		path.add(new Pair<>(new AnyTransition(),0));
		paths.add(path);
				
		//RegPassFailState 16
		path = new ArrayList<>();
		path.add(new Pair<>(new AnyTransition(),14));
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
