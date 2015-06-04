package com.mailrest.maildal.model;

import com.noorq.casser.mapping.annotation.UDT;

@UDT
public interface MessageHeaders {

	String from();
	
	String to();
	
	String subject();
	
}
