package com.collabera.ecommerce.states;

import com.collabera.ecommerce.dao.InvoiceDao;
import com.collabera.fsm.State;

public class RefundState implements State {

	String message;
	
	@Override
	public void act(String input) {
		String[] options = input.split("\\s+");
		int index = Integer.parseInt(options[1]);

		InvoiceDao.retrieve(index).ifPresentOrElse(i->{
			if(i.refunded){
				message = "That invoice was already refunded";
			}
			else {
				i.refunded = true;
				InvoiceDao.updateInvoice(i);
				message = "Successfully refunded invoice number #"+index;
				
			}
		},()->{});
	}

	@Override
	public String getOutput() {
		return message;
	}

}
