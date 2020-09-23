package com.collabera.ecommerce.states;

import com.collabera.ecommerce.ECommerceTerminalApp;
import com.collabera.ecommerce.dao.Invoice;
import com.collabera.ecommerce.dao.InvoiceDao;
import com.collabera.ecommerce.dao.Order;
import com.collabera.ecommerce.dao.OrderDao;
import com.collabera.ecommerce.dao.RetailItemDao;
import com.collabera.fsm.State;

public class AddItemState implements State {

	StringBuilder output;
	
	@Override
	public void act(String input) {
		String[] options = input.split("\\s+");
		Invoice invo = InvoiceDao.ensureActiveInvoice(
			ECommerceTerminalApp.getSingleton().user);
		
		output = new StringBuilder("Added Item(s) ");
		
		for(int i=1; i<options.length; i++) {
			RetailItemDao
			.getByCode(options[i])
			.ifPresent((ri)->{
				Order order = new Order();
				order.invoice_id = invo.id;
				order.item_id = ri.id;
				
				output.append(ri.code);
				output.append(' ');
				
				OrderDao.addOrder(order);
			});
		}
	}

	@Override
	public String getOutput() {
		return output.toString();
	}

}
