package com.mailrest.maildal.model;

import com.noorq.casser.mapping.annotation.UDT;

@UDT
public interface MessageSender {

	String sender();
	
	String sendingIp();
	
	String environment();

}
