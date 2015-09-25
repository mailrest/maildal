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
package com.mailrest.maildal.repository;

import static com.noorq.casser.core.Query.eq;

import java.util.Date;
import java.util.Optional;

import scala.Option;
import scala.concurrent.Future;

import com.datastax.driver.core.ResultSet;
import com.mailrest.maildal.gen.Generators;
import com.mailrest.maildal.model.Message;
import com.mailrest.maildal.model.MessageEvent;
import com.mailrest.maildal.model.MessageType;
import com.noorq.casser.core.Casser;
import com.noorq.casser.support.Fun;

public interface MessageRepository extends AbstractRepository {

	static final int TWO_MONTHS_IN_SECONDS = 60 * 24 * 60;
	
	static final Message message = Casser.dsl(Message.class);
	
	default Optional<Message> getMessage(String messageId) {
		
		return session()
				.select(Message.class)
				.where(message::messageId, eq(messageId))
				.single()
				.sync();
		
	}
	
	default Future<Option<Message>> findMessage(String messageId, DomainRef domainRef) {
		
		return session()
				.select(Message.class)
				.where(message::messageId, eq(messageId))
				.onlyIf(message::accountId, eq(domainRef.accountId()))
				.onlyIf(message::domainId, eq(domainRef.domainId()))
				.single()
				.future();
		
	}
	
	interface NewMessage {
		
		MessageType messageType();
		
		Date deliveryAt();
		
		String accountId();
		
		String domainId();
		
		String collisionId();
		
		String from();
		
	    String to();

	    String cc();

	    String bcc();
	    
		String subject();
		
		String textBody();

		String htmlBody();

	}
	
	default Future<Fun.Tuple2<ResultSet, String>> createMessage(NewMessage newMessage) {
		
		String messageId = Generators.MESSAGE_ID.next(newMessage.domainId());

		return session()
			.insert()
			.value(message::messageId, messageId)
			.value(message::createdAt, new Date())
			.value(message::deliveryAt, newMessage.deliveryAt())
			.value(message::messageType, newMessage.messageType())
			.value(message::accountId, newMessage.accountId())
			.value(message::domainId, newMessage.domainId())
			.value(message::collisionId, newMessage.collisionId())
			.value(message::fromRecipient, newMessage.from())
			.value(message::toRecipients, newMessage.to())
			.value(message::ccRecipients, newMessage.cc())
			.value(message::bccRecipients, newMessage.bcc())
			.value(message::subject, newMessage.subject())
			.value(message::textBody, newMessage.textBody())
			.value(message::htmlBody, newMessage.htmlBody())
			.usingTtl(TWO_MONTHS_IN_SECONDS)
			.future(messageId);
		
	}
	
	default Future<ResultSet> deleteMessage(String messageId, DomainRef domainRef) {
		
		return session()
				.delete()
				.where(message::messageId, eq(messageId))
				.onlyIf(message::accountId, eq(domainRef.accountId()))				
				.onlyIf(message::domainId, eq(domainRef.domainId()))
				.future();
		
	}	
	
	default Future<ResultSet> addMessageEvent(String messageId, MessageEvent event) {
		
		return session()
				.update()
				.append(message::events, event)
				.where(message::messageId, eq(messageId))
				.future();
		
	}
	
}
