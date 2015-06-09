/*
 *      Copyright (C) 2015 Noorq, Inc.
 *      All rights reserved.
 */
package com.mailrest.maildal.repository;

import static com.noorq.casser.core.Query.eq;
import static com.noorq.casser.core.Query.lt;
import static com.noorq.casser.core.Query.desc;

import java.util.Date;
import java.util.UUID;
import java.util.stream.Stream;

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
import com.noorq.casser.mapping.convert.TimeUUIDUtil;

public interface MessageLogRepository extends AbstractRepository {

	static final MessageLog messageLog = Casser.dsl(MessageLog.class);

	static final long ONE_DAY_MILLISECONDS = 24L * 3600L * 1000L;
	
	default Future<Stream<MessageLog>> getMessageLog(String accountId, String domainId, Date endAt, int max) {
		
		Date dayAt = daily(endAt);
		
		return session()
				.select(MessageLog.class)
				.where(messageLog::accountId, eq(accountId))
				.and(messageLog::domainId, eq(domainId))
				.and(messageLog::dayAt, eq(dayAt))
				.and(messageLog::eventAt, lt(endAt))
				.orderBy(desc(messageLog::eventAt))
				.limit(max)
				.future();
		
	}
	
	default Future<ResultSet> logMessageDeliveryAttempt(
			Message message, 
			Date eventAt,
			MessageAction action, 
			MessageDelivery delivery,
			MessageSender sender,
			MessageRecipient recipient) {
	
		UUID eventTime = TimeUUIDUtil.createTimeUUID(eventAt);
		
		return session()
				.insert()
				.value(messageLog::accountId, message.accountId())
				.value(messageLog::domainId, message.domainId().toLowerCase())
				.value(messageLog::dayAt, daily(eventAt))
				.value(messageLog::eventAt, eventTime)
				.value(messageLog::messageId, message.messageId())
				.value(messageLog::publicId, message.publicId())
				.value(messageLog::action, action)
				.value(messageLog::delivery, delivery)
				.value(messageLog::sender, sender)
				.value(messageLog::recipient, recipient)
				.value(messageLog::headers, new HeadersDelegate(message))
				.future();
		
	}

	default Date daily(Date eventAt) {
		
		long timestamp = eventAt.getTime();
		
		long diff = timestamp % ONE_DAY_MILLISECONDS;
		
		return new Date(timestamp - diff);
		
	}

	class HeadersDelegate implements MessageHeaders {

		final Message message;
		
		HeadersDelegate(Message message) {
			this.message = message;
		}
		
		@Override
		public String fromRecipients() {
			return message.fromRecipients();
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
