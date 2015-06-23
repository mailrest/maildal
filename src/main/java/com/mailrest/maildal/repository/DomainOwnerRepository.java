/*
 *      Copyright (C) 2015 Noorq, Inc.
 *      All rights reserved.
 */
package com.mailrest.maildal.repository;

import static com.noorq.casser.core.Query.desc;
import static com.noorq.casser.core.Query.eq;
import static com.noorq.casser.core.Query.in;

import java.util.Date;
import java.util.List;

import scala.Option;
import scala.concurrent.Future;

import com.datastax.driver.core.ResultSet;
import com.mailrest.maildal.model.DomainOwner;
import com.noorq.casser.core.Casser;
import com.noorq.casser.support.Fun;

public interface DomainOwnerRepository extends AbstractRepository {

	static final DomainOwner domainOwner = Casser.dsl(DomainOwner.class);
	
	default Future<Option<Fun.Tuple1<String>>> findOwner(String domainId) {
		
		return session()
				.select(domainOwner::accountId)
				.where(domainOwner::domainId, eq(domainId))
				.orderBy(desc(domainOwner::verifiedAt))
				.single()
				.future();
		
	}
	
	default Future<ResultSet> addVerifiedDomain(DomainRef domainRef, Date verifiedAt) {
		
		return session()
				.upsert()
				.value(domainOwner::domainId, domainRef.domainId())
				.value(domainOwner::verifiedAt, verifiedAt)
				.value(domainOwner::accountId, domainRef.accountId())
				.future();
		
	}
	
	default Future<ResultSet> dropDomainVerifications(DomainRef domainRef, List<Date> verifiedAt) {
		
		return session()
				.delete()
				.where(domainOwner::domainId, eq(domainRef.domainId()))
				.and(domainOwner::verifiedAt, in(verifiedAt.toArray(new Date[0])))
				.onlyIf(domainOwner::accountId, eq(domainRef.accountId()))
				.future();
		
	}
	
}
