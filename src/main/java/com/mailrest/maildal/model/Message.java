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
 *   Use TTL 60 Days
 */

@Table
public interface Message {

	/*
	 * Message id formated, example here: 20150516120002.16302.52977@lt.su
	 */

	@PartitionKey
	@MessageId
	String messageId();

	@Constraints.NotNull
	Date createdAt();
	
	@Constraints.NotNull
	Date deliveryAt();
	
	@Constraints.NotNull
	MessageType messageType();
	
	@AccountId
	String accountId();
	
	@DomainId
	String domainId();
	
	String publicId();
	
	@Constraints.NotEmpty
	String fromRecipients();
	
	@Constraints.NotEmpty
    String toRecipients();

    String ccRecipients();

    String bccRecipients();

    String templateId();
    
    Map<String, String> userVariables();
    
	String subject();
	
	String textBody();
	
	String htmlBody();
	
	String mime();
	
	List<MessageEvent> events();
}
