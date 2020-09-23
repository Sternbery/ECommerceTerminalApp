package com.collabera.ecommerce.states;

import com.collabera.ecommerce.dao.RetailItem;
import com.collabera.ecommerce.dao.RetailItemDao;
import com.collabera.fsm.State;

public class ListItemsState implements State {

	int index, length;
	
	@Override
	public void act(String input) {
		String[] options = input.split("\\s+");
		index = Integer.parseInt(options[1]);
		length = Integer.parseInt(options[2]);
		
	}

	@Override
	public String getOutput() {
		return RetailItemDao
			.getRetailItemsFromTo(index, length)
			.stream()
			.map(ri->i2l(ri))
			.reduce(new StringBuilder(), 
					(acc,next)->{
						acc.append(next);
						return acc;
					}, 
					(acc,next)->{
						acc.append(next);
						return acc;
					}).toString();
	}

	public static CharSequence i2l(RetailItem ri) {
		StringBuilder sb = new StringBuilder();
		sb.append(ri.name);
		sb.append(" ");
		sb.append(ri.code);
		sb.append(" $");
		sb.append(ri.price);
		sb.append('\n');
		return sb;
	}
}
