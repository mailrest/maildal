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

import java.util.List;

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
	MessageRecipient fromRecipient();

	/**
	 *  to: field in the message
	 *  
	 *   @return to field
	 */

	@Constraints.NotEmpty
	List<MessageRecipient> toRecipients();
	
	
	/**
	 *  cc: field in the message 
	 *  
	 *  @return cc field
	 */
	
	List<MessageRecipient> ccRecipients();
	
	/**
	 *  bcc: field in the message 
	 *  
	 *  @return bcc field
	 */
	
	List<MessageRecipient> bccRecipients();
	
	/**
	 *  subject: field in the message 
	 *  
	 *  @return subject field
	 */
		
	String subject();
	
}
