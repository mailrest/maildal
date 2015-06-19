/*
 *      Copyright (C) 2015 Noorq, Inc.
 *      All rights reserved.
 */
package com.mailrest.maildal.model;

import com.noorq.casser.mapping.annotation.UDT;

/**
 *  MessageDelivery is the information about delivery attempt, who made, when, what happened 
 *
 *  Every time when Sender tries to deliver Message it establish connection with recipient's server
 *  and by using special SMTP protocol tries to deliver it
 *  
 *  Result of the delivery stores in this UDT
 *
 */

@UDT
public interface MessageDelivery {

	/**
	 * Does server use the TLS(former SSL) encryption protocol
	 */
	
	boolean tls();
	
	/**
	 * Did server certificate verify by the sender? 
	 */
	
	boolean certificateVerified();
	
	/**
	 * Recipient's host name that was selected from MX records of the domain 
	 */
	
	String mxHost();
	
	/**
	 *  Duration is seconds of the session
	 */
	
	double sessionSeconds();
	
	/**
	 *  Final code returned by recipient's server 
	 */
	
	int code();
	
	/**
	 *  Description of the code returned by recipient's server 
	 */
	
	String description();
	
	/**
	 *  Additional message about delivery, can be error/exception name 
	 */
	
	String message();
	
}
