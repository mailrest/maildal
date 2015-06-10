/*
 *      Copyright (C) 2015 Noorq, Inc.
 *      All rights reserved.
 */
package com.mailrest.maildal.repository;

import static com.noorq.casser.core.Query.asc;
import static com.noorq.casser.core.Query.eq;
import static com.noorq.casser.core.Query.lte;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

import scala.concurrent.Future;

import com.datastax.driver.core.ResultSet;
import com.mailrest.maildal.model.MessageQueue;
import com.noorq.casser.core.Casser;
import com.noorq.casser.mapping.convert.TimeUUIDUtil;

public interface MessageQueueRepository extends AbstractRepository {

	static final MessageQueue messageQueue = Casser.dsl(MessageQueue.class);
	
	default Future<Optional<MessageQueue>> peekMessage(int bucketId) {
		
		return session()
				.select(MessageQueue.class)
				.where(messageQueue::bucket, eq(bucketId))
				.and(messageQueue::deliveryAt, lte(new Date()))
				.orderBy(asc(messageQueue::deliveryAt))
				.onlyIf(messageQueue::peeked, eq(false))
				.single()
				.future();
		
	}
	
	default Future<ResultSet> peekMessageCommit(MessageQueue msg) {
	
		return session()
				.update()
				.set(messageQueue::peeked, true)
				.where(messageQueue::bucket, eq(msg.bucket()))
				.and(messageQueue::deliveryAt, eq(msg.deliveryAt()))
				.future();
		
	}
	
	default Future<ResultSet> pollMessage(MessageQueue msg) {
		
		return session()
				.delete()
				.where(messageQueue::bucket, eq(msg.bucket()))
				.and(messageQueue::deliveryAt, eq(msg.deliveryAt()))
				.onlyIf(messageQueue::peeked, eq(true))
				.future();
		
	}
	
	default Future<ResultSet> enqueueMessage(int bucket, String messageId, Date deliveryAt, int attempt) {
		
		UUID deliveryTime = TimeUUIDUtil.createTimeUUID(deliveryAt);
		
		return session()
				.insert()
				.value(messageQueue::bucket, bucket)
				.value(messageQueue::messageId, messageId)
				.value(messageQueue::deliveryAt, deliveryTime)
				.value(messageQueue::attempt, attempt)
				.value(messageQueue::peeked, false)
				.future();
	}
	

}
