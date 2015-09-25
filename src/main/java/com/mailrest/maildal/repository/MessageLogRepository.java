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

import static com.noorq.casser.core.Query.desc;
import static com.noorq.casser.core.Query.eq;
import static com.noorq.casser.core.Query.lt;

import java.util.Date;

import scala.collection.immutable.Stream;
import scala.concurrent.Future;

import com.datastax.driver.core.ResultSet;
import com.mailrest.maildal.model.Message;
import com.mailrest.maildal.model.MessageAction;
import com.mailrest.maildal.model.MessageDelivery;
import com.mailrest.maildal.model.MessageLog;
import com.mailrest.maildal.model.MessageRecipient;
import com.mailrest.maildal.model.MessageSender;
import com.mailrest.maildal.support.MessageToHeadersAdapter;
import com.noorq.casser.core.Casser;
import com.noorq.casser.support.Timeuuid;

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
	
	default ResultSet logMessageDeliveryAttempt(
			Message message, 
			MessageRecipient recipient,
			int dayAt,
			long timestamp,
			MessageAction action, 
			MessageSender sender,
			MessageDelivery delivery) {
	
		return session()
				.insert()
				.value(messageLog::accountId, message.accountId())
				.value(messageLog::domainId, message.domainId())
				.value(messageLog::dayAt, dayAt)
				.value(messageLog::eventAt, Timeuuid.of(timestamp))
				.value(messageLog::messageId, message.messageId())
				.value(messageLog::collisionId, message.collisionId())
				.value(messageLog::action, action)
				.value(messageLog::delivery, delivery)
				.value(messageLog::sender, sender)
				.value(messageLog::recipient, recipient)
				.value(messageLog::headers, new MessageToHeadersAdapter(message))
				.sync();
		
	}
	
}
