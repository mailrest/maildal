/*
 *      Copyright (C) 2015 Noorq, Inc.
 *      All rights reserved.
 */
package com.mailrest.maildal.model;

/**
 *  MessageAction is the result if the delivery attempt made by sender 
 *
 */

public enum MessageAction {

	/**
	 *  Incoming message was received by mailrest
	 */
	RECEIVED,
	
	/**
	 *  Message has been placed in queue, before that it was checked in unsubscribed list
	 */
	ACCEPTED, 
	
	/**
	 *  Delivery attempt was rejected by server
	 */
	REJECTED,
	
	/**
	 *  Successfully sent email and it was accepted by the recipient email server
	 */
	DELIVERED,
	
	/**
	 *  Could not deliver the email to the recipient email server for some reason
	 */
	FAILED,

	/**
	 *  Recipient clicked on the unsubscribe link
	 */
	UNSUBSCRIBED;
	
}
