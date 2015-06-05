/*
 *      Copyright (C) 2015 Noorq, Inc.
 *      All rights reserved.
 */
package com.mailrest.maildal.model;

import java.util.List;

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

	MessageType messageType();
	
	String accountId();
	
	String domain();
	
	String publicId();
	
	String from();
	
    String to();

    String cc();

    String bcc();

	String subject();
	
	String body();
	
	String mime();
	
	List<MessageEvent> events();
}
