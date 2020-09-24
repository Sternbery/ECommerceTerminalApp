package com.collabera.fsm;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.collabera.tools.Pair;

public class FiniteStateMachine{
	private int currentState;
	private State[] states;
	
	
	public static final int RETRY = 0;
	public static final int TAKE_FIRST = 1;
	public static final int TAKE_FIRST_DEFAULT = 2;
	public int handle_ambiguous_input = TAKE_FIRST;
	
	List<List<Pair<Transition,Integer>>> paths;
	
	public FiniteStateMachine(State[] myStates,List<List<Pair<Transition,Integer>>> mypaths) {
		this.currentState = 0;
		this.states = myStates;
		this.paths = mypaths;

		if(states.length != paths.size())
			throw new IllegalArgumentException("states and paths must have the same number of items");
	}
	
	public int getStateIndex() {
		return currentState;
	}
	
	@SuppressWarnings("unchecked")
	public State getState() {
		return states[currentState];
	}
	
	public boolean isInTerminalState() {
		return this.paths.get(currentState).isEmpty();
	}
	/**
	 * Returns true if the input caused the state to change, false otherwise.
	 * @param input
	 * @return
	 */
	public MatchResult observe(String input) {
		List<Pair<Transition,Integer>> found =
		this.paths
			.get(currentState)
			.stream()
			.filter(p->p.getT1().checkAccepts(input))
			.collect(Collectors.toList());
		
		if(found.size() == 0 )
			return MatchResult.NONE;
		if(found.size() == 1)
			return this.changeState(found.get(0).getT2(), input);
		
		found.forEach(System.out::println);
		return MatchResult.MANY;
	}
	
	private MatchResult changeState(Integer index, String input) {
		currentState = index;
		getState().act(input);
		return MatchResult.ONE;
	}
}
