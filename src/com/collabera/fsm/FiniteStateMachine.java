package com.collabera.fsm;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import com.collabera.tools.Pair;

public class FiniteStateMachine{
	private int currentState;
	private State[] states;
	
	
	public static final int RETRY = 0;
	public static final int TAKE_FIRST = 1;
	public static final int TAKE_FIRST_DEFAULT = 2;
	public int handle_ambiguous_input = TAKE_FIRST;
	
	//private HashMap<Integer,List<Integer>> transes;
	private List<Pair<Integer,Integer>> paths;
	
	public FiniteStateMachine(State[] myStates, List<Pair<Integer,Integer>> myPaths) {
		this.currentState = 0;
		this.states = myStates;
		this.paths = myPaths;
	}
	
	@SuppressWarnings("unchecked")
	public State getState() {
		return states[currentState];
	}
	
	public boolean isInTerminalState() {
		return !paths.parallelStream().anyMatch(p->p.getT1().intValue()==currentState);
	}
	/**
	 * Returns true if the input caused the state to change, false otherwise.
	 * @param input
	 * @return
	 */
	public MatchResult observe(String input) {
		List<Pair<State,Integer>> endpoints = paths.stream()
			.filter(p->p.getT1().intValue()==currentState)
			.map(p->p.getT2())
			.map(i->new Pair<>(states[i],i))
			.filter(p->p.getT1().check(input))
			.collect(Collectors.toList());
		
		
		if(endpoints.isEmpty()) {
			return MatchResult.NONE;
		}
		else if(endpoints.size()==1) {
			
			return MatchResult.ONE;
		}
		else{
			
			List<Pair<State,Integer>> nodefs = endpoints.parallelStream().filter(p->!p.getT1().isDefault()).collect(Collectors.toList());
			if(nodefs.isEmpty()) {
				if(this.handle_ambiguous_input == RETRY)
					return MatchResult.MANY;
				if(this.handle_ambiguous_input == TAKE_FIRST || this.handle_ambiguous_input == TAKE_FIRST_DEFAULT)
					return this.chageState(endpoints.get(0).getT2(), input);
			}
			else if(nodefs.size() == 1) {
				return this.chageState(nodefs.get(0).getT2(), input);
			}
			else {
				
			}
			
		}
		//System.out
		return MatchResult.MANY;

	}
	
	private MatchResult chageState(Integer index, String input) {
		currentState = index;
		getState().act(input);
		return MatchResult.ONE;
	}
}