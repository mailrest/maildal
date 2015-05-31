package com.mailrest.maildal.dsl;

import java.util.List;

import com.noorq.casser.mapping.annotation.Table;

@Table
public interface Message {

	/*
	 * Message id formated, example here: 20150516120002.16302.52977@lt.su
	 */

	String messageId();

	String accountId();
	
	String clientUserId();
	
	List<MessageEvent> events();
	
	String from();
	
    String to();

    String cc();

    String bcc();

	String subject();
	
	String body();
	
	String mime();
	
}
