/*
 *      Copyright (C) 2015 Noorq, Inc.
 *      All rights reserved.
 */
package com.mailrest.maildal.model;

import java.util.Date;
import java.util.List;
import java.util.Map;

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
	 *  @retrun message id
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
	 *  User defined id to track massages, usually it is the out client's customer id 
	 *  
	 *  @return public id
	 */
	
	String publicId();
	
	/**
	 *  from: field  
	 *  
	 *  @return from field
	 */
	
	@Constraints.NotEmpty
	String fromRecipients();

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
	 *  Template that was used to compose this message subject/body 
	 *  
	 *  @return template id
	 */

    String templateId();
    
    /**
     *  User variables that was used in Template to compose the message subject/body 
     *  
     *  @return user variables used to build message base on template
     */
    
    Map<String, String> userVariables();
    
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
	 *  Created MIME format of the message, with subject, body, attachments 
	 *  that will be actually send 
	 *  
	 *  @return formatted MIME message
	 */
	
	String mime();
	
	/**
	 *  Message events, attempts of the delivery 
	 *  
	 *  @return message events
	 */
	
	List<MessageEvent> events();
}
