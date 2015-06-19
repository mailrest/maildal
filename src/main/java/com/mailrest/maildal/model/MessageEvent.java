/*
 *      Copyright (C) 2015 Noorq, Inc.
 *      All rights reserved.
 */
package com.mailrest.maildal.model;

import java.util.UUID;

import com.noorq.casser.mapping.annotation.Constraints;
import com.noorq.casser.mapping.annotation.Types;
import com.noorq.casser.mapping.annotation.UDT;

/**
 *  MessageEvent is using to store information about delivery attempt in the Message table
 *  
 *  Every time when Sender tries to deliver Message it creates this event and adds this to Message table
 *
 */

@UDT
public interface MessageEvent {

	/**
	 *  Delivery attempt time 
	 */
	
	@Types.Timeuuid
	@Constraints.NotNull
	UUID eventAt();
	
	/**
	 *  Action that was made 
	 */
	
	@Constraints.NotNull
	MessageAction action();
	
	/**
	 *  Actual recipient of the Message 
	 */
	
	@Constraints.NotNull
	MessageRecipient recipient();
	
}
