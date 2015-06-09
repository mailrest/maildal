/*
 *      Copyright (C) 2015 Noorq, Inc.
 *      All rights reserved.
 */
package com.mailrest.maildal.model;

import java.util.Date;
import java.util.List;
import java.util.Map;

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

	@Constraints.NotEmpty
	@PartitionKey
	String messageId();

	@Constraints.NotNull
	Date createdAt();
	
	@Constraints.NotNull
	MessageType messageType();
	
	@Constraints.NotEmpty
	String accountId();
	
	@Constraints.NotEmpty
	@Constraints.LowerCase
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
    
	@Constraints.NotEmpty
	String subject();
	
	String textBody();
	
	String htmlBody();
	
	String mime();
	
	List<MessageEvent> events();
}
