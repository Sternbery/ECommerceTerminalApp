package com.collabera.ecommerce.states;

import java.util.Optional;

import com.collabera.ecommerce.ECommerceTerminalApp;
import com.collabera.ecommerce.dao.Invoice;
import com.collabera.ecommerce.dao.InvoiceDao;
import com.collabera.fsm.State;

public class CheckoutState implements State {

	String output;
	
	@Override
	public void act(String input) {
		Optional<Invoice> invo = InvoiceDao.getActiveInvoice(ECommerceTerminalApp.getSingleton().user);
		
		invo.ifPresentOrElse((i)->{
			i.finished = true;
			i.date_finished = new java.sql.Date(new java.util.Date().getTime());
			InvoiceDao.updateInvoice(i);
			output = "successfully checked out shopping cart. Your invoice number is "+i.id;
		}, ()->{
			output = "error there are no items in you shopping cart";
		});
	}

	@Override
	public String getOutput() {
		return output;
	}

}
