/*
 *      Copyright (C) 2015 Noorq, Inc.
 *      All rights reserved.
 */
package com.mailrest.maildal.repository;

import static com.noorq.casser.core.Query.eq;
import static com.noorq.casser.core.Query.gte;
import static com.noorq.casser.core.Query.lt;

import java.util.Date;
import java.util.stream.Stream;

import scala.concurrent.Future;

import com.datastax.driver.core.ResultSet;
import com.mailrest.maildal.model.MessageStatsDaily;
import com.noorq.casser.core.Casser;

public interface MessageStatsDailyRepository extends AbstractRepository {

	static final MessageStatsDaily messageStatsDaily = Casser.dsl(MessageStatsDaily.class);
	
	static final long ONE_DAY_MILLISECONDS = 24L * 3600L * 1000L;
	
	default Future<Stream<MessageStatsDaily>> getStatsRange(String accountId, String domain, Date startAt, Date endAt) {

		return session()
				.select(MessageStatsDaily.class)
				.where(messageStatsDaily::accountId, eq(accountId))
				.and(messageStatsDaily::domain, eq(domain.toLowerCase()))
				.and(messageStatsDaily::dayAt, gte(startAt))
				.and(messageStatsDaily::dayAt, lt(endAt))
				.future();
		
	}
	
	default Future<ResultSet> incrementReceived(String accountId, String domain, Date eventAt) {
		
		return session()
				.update()
				.increment(messageStatsDaily::received)
				.where(messageStatsDaily::accountId, eq(accountId))
				.and(messageStatsDaily::domain, eq(domain.toLowerCase()))
				.and(messageStatsDaily::dayAt, eq(daily(eventAt)))
				.future();
		
	}
	
	default Future<ResultSet> incrementDelivered(String accountId, String domain, Date eventAt) {
		
		return session()
				.update()
				.increment(messageStatsDaily::delivered)
				.where(messageStatsDaily::accountId, eq(accountId))
				.and(messageStatsDaily::domain, eq(domain.toLowerCase()))
				.and(messageStatsDaily::dayAt, eq(daily(eventAt)))
				.future();
		
	}
	
	default Future<ResultSet> incrementDropped(String accountId, String domain, Date eventAt) {
		
		return session()
				.update()
				.increment(messageStatsDaily::dropped)
				.where(messageStatsDaily::accountId, eq(accountId))
				.and(messageStatsDaily::domain, eq(domain.toLowerCase()))
				.and(messageStatsDaily::dayAt, eq(daily(eventAt)))
				.future();
		
	}
	
	default Future<ResultSet> incrementUnsubscribed(String accountId, String domain, Date eventAt) {
		
		return session()
				.update()
				.increment(messageStatsDaily::unsubscribed)
				.where(messageStatsDaily::accountId, eq(accountId))
				.and(messageStatsDaily::domain, eq(domain.toLowerCase()))
				.and(messageStatsDaily::dayAt, eq(daily(eventAt)))
				.future();
		
	}
	
	default Date daily(Date eventAt) {
		
		long timestamp = eventAt.getTime();
		
		long diff = timestamp % ONE_DAY_MILLISECONDS;
		
		return new Date(timestamp - diff);
		
	}
	
}
