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
import java.util.UUID;

import scala.Option;
import scala.concurrent.Future;

import com.datastax.driver.core.ResultSet;
import com.mailrest.maildal.model.DomainVerificationQueue;
import com.noorq.casser.core.Casser;
import com.noorq.casser.support.Timeuuid;

public interface DomainVerificationQueueRepository extends AbstractRepository {

	static final DomainVerificationQueue domainVerificationQueue = Casser.dsl(DomainVerificationQueue.class);
	
	default Future<Option<DomainVerificationQueue>> peekDomain(int bucketId) {
		
		return session()
				.select(DomainVerificationQueue.class)
				.where(domainVerificationQueue::bucket, eq(bucketId))
				.and(domainVerificationQueue::verifyAt, lte(Timeuuid.maxOf(new Date())))
				.orderBy(asc(domainVerificationQueue::verifyAt))
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
		
		UUID verifyTime = Timeuuid.of(verifyAt);
		
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
