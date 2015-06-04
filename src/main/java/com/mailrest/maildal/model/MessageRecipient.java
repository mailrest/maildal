package com.mailrest.maildal.model;

import com.noorq.casser.mapping.annotation.UDT;

@UDT
public interface MessageRecipient {

	String recipient();
	
	String recipientDomain();

}
