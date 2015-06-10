/*
 *      Copyright (C) 2015 Noorq, Inc.
 *      All rights reserved.
 */
package com.mailrest.maildal.repository;

import static com.noorq.casser.core.Query.eq;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import scala.concurrent.Future;

import com.datastax.driver.core.ResultSet;
import com.mailrest.maildal.model.AccountDomain;
import com.mailrest.maildal.model.DomainVerificationEvent;
import com.mailrest.maildal.model.DomainVerificationStatus;
import com.noorq.casser.core.Casser;

public interface AccountDomainRepository extends AbstractRepository {

	static final AccountDomain accountDomain = Casser.dsl(AccountDomain.class);
	
	
	default Future<Stream<AccountDomain>> findDomains(String accountId) {
		
		return session()
				.select(AccountDomain.class)
				.where(accountDomain::accountId, eq(accountId))
				.future();
		
	}
	
	default Future<Optional<List<DomainVerificationEvent>>> getVerificationEvents(String accountId, String domainId) {
		
		return session()
				.select(accountDomain::events)
				.where(accountDomain::accountId, eq(accountId))
				.and(accountDomain::domainId, eq(domainId))
				.single()
				.map(t -> t._1)
				.future();
		
	}
	
	default Future<ResultSet> addVerificationEvent(String accountId, String domainId, DomainVerificationEvent event) {
		
		return session()
				.update()
				.append(accountDomain::events, event)
				.set(accountDomain::lastStatus, event.status())
				.where(accountDomain::accountId, eq(accountId))
				.and(accountDomain::domainId, eq(domainId))
				.future();
		
	}
	
	default Future<ResultSet> addAccountDomain(String accountId, String domainId, String domainIdn) {
		
		return session()
				.upsert()
				.value(accountDomain::accountId, accountId)
				.value(accountDomain::domainId, domainId)
				.value(accountDomain::domainIdn, domainIdn)
				.value(accountDomain::createdAt, new Date())
				.value(accountDomain::lastStatus, DomainVerificationStatus.ACCEPTED)
				.future();
		
	}
	
	default Future<ResultSet> dropAccountDomain(String accountId, String domainId) {
		
		return session()
				.delete()
				.where(accountDomain::accountId, eq(accountId))
				.and(accountDomain::domainId, eq(domainId))
				.future();
		
	}
	
}
