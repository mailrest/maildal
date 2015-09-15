/*
 *      Copyright (C) 2015 Noorq, Inc.
 *      All rights reserved.
 */
package com.mailrest.maildal.repository;

import static com.noorq.casser.core.Query.desc;
import static com.noorq.casser.core.Query.eq;
import static com.noorq.casser.core.Query.lt;

import java.util.Date;
import java.util.UUID;
import scala.collection.immutable.Stream;

import scala.concurrent.Future;

import com.datastax.driver.core.ResultSet;
import com.mailrest.maildal.model.Message;
import com.mailrest.maildal.model.MessageAction;
import com.mailrest.maildal.model.MessageDelivery;
import com.mailrest.maildal.model.MessageHeaders;
import com.mailrest.maildal.model.MessageLog;
import com.mailrest.maildal.model.MessageRecipient;
import com.mailrest.maildal.model.MessageSender;
import com.noorq.casser.core.Casser;

public interface MessageLogRepository extends AbstractRepository {

	static final MessageLog messageLog = Casser.dsl(MessageLog.class);

	static final long ONE_DAY_MILLISECONDS = 24L * 3600L * 1000L;

	default Future<Stream<MessageLog>> findMessageLogs(DayRef dayRef, int max) {
		
		return session()
				.select(MessageLog.class)
				.where(messageLog::accountId, eq(dayRef.accountId()))
				.and(messageLog::domainId, eq(dayRef.domainId()))
				.and(messageLog::dayAt, eq(dayRef.dayAt()))
				.orderBy(desc(messageLog::eventAt))
				.limit(max)
				.future();
		
	}
	
	default Future<Stream<MessageLog>> findMessageLogs(DayRef dayRef, Date before, int max) {
		
		return session()
				.select(MessageLog.class)
				.where(messageLog::accountId, eq(dayRef.accountId()))
				.and(messageLog::domainId, eq(dayRef.domainId()))
				.and(messageLog::dayAt, eq(dayRef.dayAt()))
				.and(messageLog::eventAt, lt(before))
				.orderBy(desc(messageLog::eventAt))
				.limit(max)
				.future();
		
	}
	
	default Future<ResultSet> logMessageDeliveryAttempt(
			Message message, 
			int dayAt,
			UUID eventAt,
			MessageAction action, 
			MessageDelivery delivery,
			MessageSender sender,
			MessageRecipient recipient) {
	
		return session()
				.insert()
				.value(messageLog::accountId, message.accountId())
				.value(messageLog::domainId, message.domainId())
				.value(messageLog::dayAt, dayAt)
				.value(messageLog::eventAt, eventAt)
				.value(messageLog::messageId, message.messageId())
				.value(messageLog::collisionId, message.collisionId())
				.value(messageLog::action, action)
				.value(messageLog::delivery, delivery)
				.value(messageLog::sender, sender)
				.value(messageLog::recipient, recipient)
				.value(messageLog::headers, new HeadersDelegate(message))
				.future();
		
	}


	class HeadersDelegate implements MessageHeaders {

		final Message message;
		
		HeadersDelegate(Message message) {
			this.message = message;
		}
		
		@Override
		public String fromRecipients() {
			return message.fromRecipient();
		}

		@Override
		public String toRecipients() {
			return message.toRecipients();
		}

		@Override
		public String ccRecipients() {
			return message.ccRecipients();
		}

		@Override
		public String bccRecipients() {
			return message.bccRecipients();
		}

		@Override
		public String subject() {
			return message.subject();
		}
		
	}
	
}
