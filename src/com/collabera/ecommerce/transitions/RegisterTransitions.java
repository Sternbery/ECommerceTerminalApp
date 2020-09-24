package com.collabera.ecommerce.transitions;

import java.util.regex.Pattern;

import com.collabera.ecommerce.ECommerceTerminalApp;
import com.collabera.ecommerce.dao.UserDao;
import com.collabera.fsm.Transition;

public class RegisterTransitions {

	//login transitions
	public static Transition getAttemptLoginTransition() {
		return (input)->ECommerceTerminalApp.getSingleton().attemptLogin(input);
	}
	public static Transition getLoginFailedTransition() {
		return (input)->!ECommerceTerminalApp.getSingleton().attemptLogin(input);
	}
	
	//register transitions
	public static final Pattern EMAIL_PATTERN = Pattern.compile("^([\\w\\d_\\-\\.]+)@([\\w\\d_\\-\\.]+)\\.([\\w]{2,5})$");	
	public static boolean isEmailInvalid(String email) {
		return !EMAIL_PATTERN.matcher(email).matches();
	}
	public static Transition getCheckEmailInvalidTransition(String ignore) {
		return (input)->	!ignore.contentEquals(input) &&
							RegisterTransitions.isEmailInvalid(input);
	}
	
	public static boolean isEmailInUse(String email) {
		return UserDao.getUser(email).isPresent();
	}
	public static Transition getCheckEmailInUseTransition(String ignore) {
		return (input)->	!ignore.contentEquals(input) &&
							!RegisterTransitions.isEmailInvalid(input) &&
							RegisterTransitions.isEmailInUse(input);
	}

	
	
	public static Transition getEmailGoodTransition(String ignore) {
		return (input)-> 	!ignore.contentEquals(input) &&
							!RegisterTransitions.isEmailInvalid(input) &&
							!RegisterTransitions.isEmailInUse(input);
	}
	
	
	
	public static Transition getReEnterPasswordTransition() {
		return (input)-> ECommerceTerminalApp.getSingleton().passwordHash.equals(input);
	}
	public static Transition getReEnterPasswordailTransition() {
		return (input)-> !ECommerceTerminalApp.getSingleton().passwordHash.equals(input);
	}
}
