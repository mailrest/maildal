/*
 *      Copyright (C) 2015 Noorq, Inc.
 *      All rights reserved.
 */
package com.mailrest.maildal.model;

import com.noorq.casser.mapping.annotation.Constraints;
import com.noorq.casser.mapping.annotation.UDT;

/**
 *  MessageHeaders structure is using to keep Message headers separated from Message itself
 *  for log purposes
 *  
 *  Every time when a new MessageLog record created all those headers are copied from Message to
 *  this structure
 *  
 *  MessageLog is using for visualization purposes of the logs in UI, so, having this information
 *  here increase access to it
 *
 */

@UDT
public interface MessageHeaders {

	/**
	 *  from: field in the message 
	 *  
	 *  @return from field
	 */
	
	@Constraints.NotEmpty
	String fromRecipients();

	/**
	 *  to: field in the message
	 *  
	 *   @return to field
	 */

	@Constraints.NotEmpty
	String toRecipients();
	
	
	/**
	 *  cc: field in the message 
	 *  
	 *  @return cc field
	 */
	
	String ccRecipients();
	
	/**
	 *  bcc: field in the message 
	 *  
	 *  @return bcc field
	 */
	
	String bccRecipients();
	
	/**
	 *  subject: field in the message 
	 *  
	 *  @return subject field
	 */
		
	String subject();
	
}
