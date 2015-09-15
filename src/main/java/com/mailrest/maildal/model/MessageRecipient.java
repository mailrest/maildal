/*
 *      Copyright (C) 2015 Noorq, Inc.
 *      All rights reserved.
 */
package com.mailrest.maildal.model;

import com.mailrest.maildal.model.constraint.EmailId;
import com.noorq.casser.mapping.annotation.UDT;

/**
 *  MessageRecipient holds information about particular recipient of the Message 
 */

@UDT
public interface MessageRecipient {

	/**
	 *  Full text of the recipient name extracted from toRecipients, ccRecipients and bccRecipients fields in the Message
	 *  
	 *  @return recipient name or null
	 */
	
	String recipientName();
	
	/**
	 *  Email that was extracted from recipient field and normalized by IDN and lower cased
	 *  
	 *  @return parsed and extracted email from recipient, in order to check correctness
	 */
	
	@EmailId
	String recipientEmail();

}
