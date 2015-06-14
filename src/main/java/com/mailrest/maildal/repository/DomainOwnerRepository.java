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
import java.util.Optional;

import scala.concurrent.Future;

import com.datastax.driver.core.ResultSet;
import com.mailrest.maildal.model.DomainOwner;
import com.noorq.casser.core.Casser;
import com.noorq.casser.support.Fun;

public interface DomainOwnerRepository extends AbstractRepository {

	static final DomainOwner domainOwner = Casser.dsl(DomainOwner.class);
	
	default Future<Optional<Fun.Tuple1<String>>> findOwner(String domainId) {
		
		return session()
				.select(domainOwner::accountId)
				.where(domainOwner::domainId, eq(domainId))
				.orderBy(desc(domainOwner::verifiedAt))
				.single()
				.future();
		
	}
	
	default Future<ResultSet> addVerifiedDomain(String accountId, String domainId, Date verifiedAt) {
		
		return session()
				.upsert()
				.value(domainOwner::domainId, domainId)
				.value(domainOwner::verifiedAt, verifiedAt)
				.value(domainOwner::accountId, accountId)
				.future();
		
	}
	
	default Future<ResultSet> dropDomainVerifications(String accountId, String domainId, List<Date> verifiedAt) {
		
		return session()
				.delete()
				.where(domainOwner::domainId, eq(domainId))
				.and(domainOwner::verifiedAt, in(verifiedAt.toArray(new Date[0])))
				.onlyIf(domainOwner::accountId, eq(accountId))
				.future();
		
	}
	
}
