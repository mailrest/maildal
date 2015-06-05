/*
 *      Copyright (C) 2015 Noorq, Inc.
 *      All rights reserved.
 */
package com.mailrest.maildal.model;


public enum MessageAction {

	// incoming message was received by mailrest
	RECEIVED,
	
	// message has been placed in queue
	ACCEPTED, 
	
	// request was rejected
	REJECTED,
	
	// sent email and was accepted by the recipient email server
	DELIVERED,
	
	// could not deliver the email to the recipient email server
	FAILED,

	// recipient clicked on the unsubscribe link
	UNSUBSCRIBED;
	
}
