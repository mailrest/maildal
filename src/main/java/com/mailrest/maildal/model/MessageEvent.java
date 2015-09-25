/*
 *      Copyright (C) 2015 Noorq, Inc.
 *
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
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
	 *  
	 *  @return immutable message delivery timestamp
	 */
	
	@Types.Timeuuid
	@Constraints.NotNull
	UUID eventAt();
	
	/**
	 *  Action that was made 
	 *  
	 *  @return message action result
	 */
	
	@Constraints.NotNull
	MessageAction action();
	
	/**
	 *  Actual recipient of the Message 
	 *  
	 *  @return recipient of the message
	 */
	
	@Constraints.NotNull
	MessageRecipient recipient();
	
}
