/*
 *      Copyright (C) 2015 Noorq, Inc.
 *      All rights reserved.
 */
package com.mailrest.maildal.model;

import java.util.Date;
import java.util.List;
import java.util.Map;

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
	String messageId();

	Date createdAt();
	
	MessageType messageType();
	
	String accountId();
	
	String domain();
	
	String publicId();
	
	String fromRecipients();
	
    String toRecipients();

    String ccRecipients();

    String bccRecipients();

    String template();
    
    Map<String, String> userVariables();
    
	String subject();
	
	String body();
	
	String mime();
	
	List<MessageEvent> events();
}
