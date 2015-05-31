package com.mailrest.maildal.dsl;


public enum EventType {

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
