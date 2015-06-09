/*
 *      Copyright (C) 2015 Noorq, Inc.
 *      All rights reserved.
 */
package com.mailrest.maildal.repository;

import static com.noorq.casser.core.Query.eq;

import java.util.Date;
import java.util.Map;
import java.util.Optional;

import scala.concurrent.Future;

import com.datastax.driver.core.ResultSet;
import com.mailrest.maildal.gen.MessageId;
import com.mailrest.maildal.model.Message;
import com.mailrest.maildal.model.MessageEvent;
import com.mailrest.maildal.model.MessageType;
import com.noorq.casser.core.Casser;
import com.noorq.casser.support.Fun;

public interface MessageRepository extends AbstractRepository {

	static final Message message = Casser.dsl(Message.class);
	
	default Future<Optional<Message>> findMessage(String messageId) {
		
		return session()
				.select(Message.class)
				.where(message::messageId, eq(messageId))
				.single()
				.future();
		
	}
	
	interface NewMessage {
		
		MessageType messageType();
		
		String accountId();
		
		String domainId();
		
		String publicId();
		
		String from();
		
	    String to();

	    String cc();

	    String bcc();

	    String templateId();
	    
	    Map<String, String> userVariables();
	    
		String subject();
		
		String body();
		
	}
	
	default Future<Fun.Tuple2<ResultSet, String>> createMessage(NewMessage newMessage) {
		
		String messageId = MessageId.next(newMessage.domainId());

		return session()
			.insert()
			.value(message::messageId, messageId)
			.value(message::createdAt, new Date())
			.value(message::messageType, newMessage.messageType())
			.value(message::accountId, newMessage.accountId())
			.value(message::domainId, newMessage.domainId())
			.value(message::publicId, newMessage.publicId())
			.value(message::fromRecipients, newMessage.from())
			.value(message::toRecipients, newMessage.to())
			.value(message::ccRecipients, newMessage.cc())
			.value(message::bccRecipients, newMessage.bcc())
			.value(message::templateId, newMessage.templateId())
			.value(message::userVariables, newMessage.userVariables())
			.value(message::subject, newMessage.subject())
			.value(message::textBody, newMessage.body())
			.future(messageId);
		
	}
	
	default Future<ResultSet> addMessageEvent(String messageId, MessageEvent event) {
		
		return session()
				.update()
				.append(message::events, event)
				.where(message::messageId, eq(messageId))
				.future();
		
	}
	
}
