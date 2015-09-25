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

import java.util.Date;
import java.util.List;

import com.mailrest.maildal.model.constraint.AccountId;
import com.mailrest.maildal.model.constraint.DomainId;
import com.mailrest.maildal.model.constraint.MessageId;
import com.noorq.casser.mapping.annotation.Constraints;
import com.noorq.casser.mapping.annotation.PartitionKey;
import com.noorq.casser.mapping.annotation.Table;

/**
 *   Messsage is the email that can be outgoing or incoming
 *  
 *   Every time when the sombody sends REST API request to send message we
 *   create this object. Here we store all necessary information to make delivery
 *
 *   Message is the complicated object that have multiple fields of recipients, body and
 *   can be repeated to the specific @Template
 *
 *   Messages can have text or html format. Html message anyway can have text underline
 *   
 *   Messages can have attached files, specially html messages
 *
 *   When message arrived by some channel it recieves new generated messageId, that is the
 *   date format + random numbers + domainId
 *
 *   Every time when we make attempt to send this message, we can have situation where
 *   some recipients recieved this message, some of them are not. This situation we need to
 *   track by MessageEvent list. Every time when successful or failed operation happens we
 *   add new event in the list. That how we can track that we need to do for the delivery
 *
 */

@Table
public interface Message {

	/**
	 *  Message id formated, unique, example here: 20150516120002.16302.52977@lt.su
	 *  
	 *  @return message id
	 */

	@PartitionKey
	@MessageId
	String messageId();

	/**
	 *  Time when the message was created
	 *  
	 *  @return immutable time of message creation
	 */
	
	@Constraints.NotNull
	Date createdAt();
		
	/**
	 *  Message type, can be INCOMING, OUTGOING or DRAFT 
	 *  
	 *  @return message type
	 */
	
	@Constraints.NotNull
	MessageType messageType();
	
	/**
	 *  Schedule time when we need to send message, valid only for OUTGOING messages 
	 *  
	 *  @return message delivery schedule time
	 */
	
	Date deliveryAt();

	/**
	 *  Corresponds to a specific @Account 
	 *  
	 *  @return account id
	 */
	
	@AccountId
	String accountId();
	
	/**
	 *  Usually we know what domainId is using to deliver or recieve message 
	 *  
	 *  @return domain id
	 */
	
	@DomainId
	String domainId();
	
	/**
	 *  User defined id to track massages, usually it is the out client's collision id 
	 *  
	 *  @return collision id
	 */
	
	String collisionId();
	
	/**
	 *  from: field  
	 *  
	 *  @return from field
	 */
	
	@Constraints.NotEmpty
	String fromRecipient();

	/**
	 *  to: field  
	 *  
	 *  @return to field
	 */

	@Constraints.NotEmpty
    String toRecipients();

	/**
	 *  cc: field  
	 *  
	 *  @return cc field
	 */

    String ccRecipients();

	/**
	 *  bcc: field  
	 *  
	 *  @return bcc field
	 */

    String bccRecipients();

    /**
     *  Subject of the message 
     *  
     *  @return subject field
     */
    
	String subject();
	
	/**
	 *  Text body of the message, can be alone or with html body
	 *  
	 *  @return text body field
	 */
	
	String textBody();

	/**
	 *  HTML body of the message, can be optional
	 *  
	 *  @return html body field
	 */

	String htmlBody();
	
	/**
	 *  Message events, attempts of the delivery 
	 *  
	 *  @return message events
	 */
	
	List<MessageEvent> events();
}
