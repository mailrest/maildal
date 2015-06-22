/*
 *      Copyright (C) 2015 Noorq, Inc.
 *      All rights reserved.
 */
package com.mailrest.maildal.repository;

import static com.noorq.casser.core.Query.eq;
import static com.noorq.casser.core.Query.gte;
import static com.noorq.casser.core.Query.lt;

import java.util.stream.Stream;

import scala.concurrent.Future;

import com.datastax.driver.core.ResultSet;
import com.mailrest.maildal.model.MessageStatsDaily;
import com.noorq.casser.core.Casser;

public interface MessageStatsDailyRepository extends AbstractRepository {

	static final MessageStatsDaily messageStatsDaily = Casser.dsl(MessageStatsDaily.class);
	
	static final long ONE_DAY_MILLISECONDS = 24L * 3600L * 1000L;
	
	default Future<Stream<MessageStatsDaily>> getStatsRange(DomainRef domainRef, int startAt, int endAt) {

		return session()
				.select(MessageStatsDaily.class)
				.where(messageStatsDaily::accountId, eq(domainRef.accountId()))
				.and(messageStatsDaily::domainId, eq(domainRef.domainId()))
				.and(messageStatsDaily::dayAt, gte(startAt))
				.and(messageStatsDaily::dayAt, lt(endAt))
				.future();
		
	}
	
	default Future<ResultSet> incrementReceived(DayRef dayRef) {
		
		return session()
				.update()
				.increment(messageStatsDaily::received)
				.where(messageStatsDaily::accountId, eq(dayRef.accountId()))
				.and(messageStatsDaily::domainId, eq(dayRef.domainId()))
				.and(messageStatsDaily::dayAt, eq(dayRef.dayAt()))
				.future();
		
	}
	
	default Future<ResultSet> incrementDelivered(DayRef dayRef) {
		
		return session()
				.update()
				.increment(messageStatsDaily::delivered)
				.where(messageStatsDaily::accountId, eq(dayRef.accountId()))
				.and(messageStatsDaily::domainId, eq(dayRef.domainId()))
				.and(messageStatsDaily::dayAt, eq(dayRef.dayAt()))
				.future();
		
	}
	
	default Future<ResultSet> incrementDropped(DayRef dayRef) {
		
		return session()
				.update()
				.increment(messageStatsDaily::dropped)
				.where(messageStatsDaily::accountId, eq(dayRef.accountId()))
				.and(messageStatsDaily::domainId, eq(dayRef.domainId()))
				.and(messageStatsDaily::dayAt, eq(dayRef.dayAt()))
				.future();
		
	}
	
	default Future<ResultSet> incrementUnsubscribed(DayRef dayRef) {
		
		return session()
				.update()
				.increment(messageStatsDaily::unsubscribed)
				.where(messageStatsDaily::accountId, eq(dayRef.accountId()))
				.and(messageStatsDaily::domainId, eq(dayRef.domainId()))
				.and(messageStatsDaily::dayAt, eq(dayRef.dayAt()))
				.future();
		
	}
	
	
}
