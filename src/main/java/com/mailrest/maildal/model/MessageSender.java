/*
 *      Copyright (C) 2015 Noorq, Inc.
 *      All rights reserved.
 */
package com.mailrest.maildal.model;

import com.noorq.casser.mapping.annotation.Constraints;
import com.noorq.casser.mapping.annotation.UDT;

/**
 *   MessageSender information that is using to track what server and 
 *   what IP of the server made delivery attempt
 *
 *   For the next time we need to select another bucket and schedule
 *   Message delivery for different sender
 *
 *   In logs we will see that Message was tried to delivery by different senders
 *   before it's dropped
 *
 */


@UDT
public interface MessageSender {

	/**
	 *   Sender's name, usually pid@hostname 
	 *   
	 *   @return sender name
	 */
	
	@Constraints.NotEmpty
	String sender();
	
	/**
	 *   Sender's public IP
	 *   
	 *   @return sender's public IP address
	 */
	
	@Constraints.NotEmpty
	String sendingIp();

}
