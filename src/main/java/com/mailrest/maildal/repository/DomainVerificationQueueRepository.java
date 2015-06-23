/*
 *      Copyright (C) 2015 Noorq, Inc.
 *      All rights reserved.
 */
package com.mailrest.maildal.repository;

import static com.noorq.casser.core.Query.asc;
import static com.noorq.casser.core.Query.eq;
import static com.noorq.casser.core.Query.lte;

import java.util.Date;
import java.util.UUID;

import scala.Option;
import scala.concurrent.Future;

import com.datastax.driver.core.ResultSet;
import com.mailrest.maildal.model.DomainVerificationQueue;
import com.noorq.casser.core.Casser;
import com.noorq.casser.mapping.convert.TimeUUIDUtil;

public interface DomainVerificationQueueRepository extends AbstractRepository {

	static final DomainVerificationQueue domainVerificationQueue = Casser.dsl(DomainVerificationQueue.class);
	
	default Future<Option<DomainVerificationQueue>> peekDomain(int bucketId) {
		
		return session()
				.select(DomainVerificationQueue.class)
				.where(domainVerificationQueue::bucket, eq(bucketId))
				.and(domainVerificationQueue::verifyAt, lte(new Date()))
				.orderBy(asc(domainVerificationQueue::verifyAt))
				.onlyIf(domainVerificationQueue::peeked, eq(false))
				.single()
				.future();
		
	}
	
	default Future<ResultSet> peekDomainCommit(DomainVerificationQueue dom) {
	
		return session()
				.update()
				.set(domainVerificationQueue::peeked, true)
				.where(domainVerificationQueue::bucket, eq(dom.bucket()))
				.and(domainVerificationQueue::verifyAt, eq(dom.verifyAt()))
				.future();
		
	}
	
	default Future<ResultSet> pollDomain(DomainVerificationQueue dom) {
		
		return session()
				.delete()
				.where(domainVerificationQueue::bucket, eq(dom.bucket()))
				.and(domainVerificationQueue::verifyAt, eq(dom.verifyAt()))
				.onlyIf(domainVerificationQueue::peeked, eq(true))
				.future();
		
	}
	
	default Future<ResultSet> enqueueDomain(int bucket, DomainRef domainRef, Date verifyAt, int attempt) {
		
		UUID verifyTime = TimeUUIDUtil.createTimeUUID(verifyAt);
		
		return session()
				.insert()
				.value(domainVerificationQueue::bucket, bucket)
				.value(domainVerificationQueue::accountId, domainRef.accountId())
				.value(domainVerificationQueue::domainId, domainRef.domainId())
				.value(domainVerificationQueue::verifyAt, verifyTime)
				.value(domainVerificationQueue::attempt, attempt)
				.value(domainVerificationQueue::peeked, false)
				.future();
	}
	

}
