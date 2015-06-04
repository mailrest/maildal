/*
 *      Copyright (C) 2015 Noorq, Inc.
 *      All rights reserved.
 */
package com.mailrest.maildal.repository;

import static com.noorq.casser.core.Query.eq;
import static com.noorq.casser.core.Query.desc;

import java.util.Date;
import java.util.Optional;

import scala.concurrent.Future;

import com.datastax.driver.core.ResultSet;
import com.mailrest.maildal.model.DomainOwner;
import com.noorq.casser.core.Casser;
import com.noorq.casser.support.Fun;

public interface DomainOwnerRepository extends AbstractRepository {

	static final DomainOwner domainOwner = Casser.dsl(DomainOwner.class);
	
	default Future<Optional<Fun.Tuple1<String>>> findOwner(String domain) {
		
		return session()
				.select(domainOwner::accountId)
				.where(domainOwner::domain, eq(domain.toLowerCase()))
				.orderBy(desc(domainOwner::verifiedAt))
				.single()
				.future();
		
	}
	
	default Future<ResultSet> addVerifiedDomain(String accountId, String domain, Date verifiedAt) {
		
		return session()
				.upsert()
				.value(domainOwner::domain, domain.toLowerCase())
				.value(domainOwner::verifiedAt, verifiedAt)
				.value(domainOwner::accountId, accountId)
				.future();
		
	}
	
	
}
