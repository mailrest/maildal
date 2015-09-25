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

import static com.noorq.casser.core.Query.asc;
import static com.noorq.casser.core.Query.eq;
import static com.noorq.casser.core.Query.lte;

import java.util.Date;
import java.util.Optional;

import scala.concurrent.Future;

import com.datastax.driver.core.ResultSet;
import com.mailrest.maildal.model.MessageQueue;
import com.noorq.casser.core.Casser;
import com.noorq.casser.support.Timeuuid;

public interface MessageQueueRepository extends AbstractRepository {

	static final MessageQueue messageQueue = Casser.dsl(MessageQueue.class);
	
	default Optional<MessageQueue> peekMessage(int bucketId) {
		
		return session()
				.select(MessageQueue.class)
				.where(messageQueue::bucket, eq(bucketId))
				.and(messageQueue::deliveryAt, lte(Timeuuid.maxOf(new Date())))
				.orderBy(asc(messageQueue::deliveryAt))
				.single()
				.sync();
		
	}
	
	default ResultSet peekMessageCommit(MessageQueue msg) {
	
		return session()
				.update()
				.set(messageQueue::peeked, true)
				.where(messageQueue::bucket, eq(msg.bucket()))
				.and(messageQueue::deliveryAt, eq(msg.deliveryAt()))
				.sync();
		
	}
	
	default ResultSet pollMessage(MessageQueue msg) {
		
		return session()
				.delete()
				.where(messageQueue::bucket, eq(msg.bucket()))
				.and(messageQueue::deliveryAt, eq(msg.deliveryAt()))
				.onlyIf(messageQueue::peeked, eq(true))
				.sync();
		
	}
	
	default Future<ResultSet> enqueueMessage(int bucket, String messageId, Date deliveryAt, int attempt) {
		
		return session()
				.insert()
				.value(messageQueue::bucket, bucket)
				.value(messageQueue::messageId, messageId)
				.value(messageQueue::deliveryAt, Timeuuid.of(deliveryAt))
				.value(messageQueue::attempt, attempt)
				.value(messageQueue::peeked, false)
				.future();
	}
	
	default ResultSet enqueueMessage(MessageQueue msg, Date deliveryAt) {
		
		return session()
				.insert()
				.value(messageQueue::bucket, msg.bucket())
				.value(messageQueue::messageId, msg.messageId())
				.value(messageQueue::deliveryAt, Timeuuid.of(deliveryAt))
				.value(messageQueue::attempt, msg.attempt() + 1)
				.value(messageQueue::peeked, false)
				.sync();
	}

}
