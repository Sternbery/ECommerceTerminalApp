package com.collabera.ecommerce.states;

import java.util.stream.Collectors;

import com.collabera.ecommerce.ECommerceTerminalApp;
import com.collabera.ecommerce.dao.InvoiceDao;
import com.collabera.ecommerce.dao.OrderDao;
import com.collabera.fsm.State;
import com.collabera.tools.Pair;

public class ListInvoicesState implements State {

	int index, length;
	
	@Override
	public void act(String input) {
		String[] options = input.split("\\s+");
		index = Integer.parseInt(options[1]);
		length = Integer.parseInt(options[2]);
	}

	@Override
	public String getOutput() {
		return "Begin invoice list from "+index+" to " + (index+length) + "\n" +
			InvoiceDao.retrieveByUse(ECommerceTerminalApp.getSingleton().user)
			.parallelStream()
			.skip(index)
			.limit(length)
			.map(i->i.toString()+"\n\t"+
					OrderDao
					.retrieveByInvoiceJoinItem(i)
					.stream()
					.map(ri->ri.toString())
					.collect(Collectors.joining("\n\t"))
				
			).collect(Collectors.joining("\n"))+
			"\nend invoice list";
			
			
	}

}
