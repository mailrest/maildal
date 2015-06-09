/*
 *      Copyright (C) 2015 Noorq, Inc.
 *      All rights reserved.
 */
package com.mailrest.maildal.model;

import com.noorq.casser.mapping.annotation.UDT;

@UDT
public interface MessageDelivery {

	boolean tls();
	
	boolean certificateVerified();
	
	String mxHost();
	
	double sessionSeconds();
	
	int code();
	
	String description();
	
	String message();
	
}
