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
import com.mailrest.maildal.model.DomainVerificationStatus;
import com.mailrest.maildal.model.DomainVerificationEvent;
import com.noorq.casser.core.Casser;
import com.noorq.casser.support.Fun;

public interface AccountDomainRepository extends AbstractRepository {

	static final AccountDomain accountDomain = Casser.dsl(AccountDomain.class);
	
	
	default Future<Stream<Fun.Tuple4<String, String, Date, DomainVerificationStatus>>> findDomains(String accountId) {
		
		return session()
				.select(accountDomain::accountId, accountDomain::domainId, accountDomain::createdAt, accountDomain::lastStatus)
				.where(accountDomain::accountId, eq(accountId))
				.future();
		
	}
	
	default Future<Optional<List<DomainVerificationEvent>>> getVerificationEvents(String accountId, String domain) {
		
		return session()
				.select(accountDomain::events)
				.where(accountDomain::accountId, eq(accountId))
				.and(accountDomain::domainId, eq(domain.toLowerCase()))
				.single()
				.map(t -> t._1)
				.future();
		
	}
	
	default Future<ResultSet> addVerificationEvent(String accountId, String domain, DomainVerificationEvent event) {
		
		return session()
				.update()
				.append(accountDomain::events, event)
				.set(accountDomain::lastStatus, event.status())
				.where(accountDomain::accountId, eq(accountId))
				.and(accountDomain::domainId, eq(domain.toLowerCase()))
				.future();
		
	}
	
	default Future<ResultSet> addAccountDomain(String accountId, String domain) {
		
		return session()
				.upsert()
				.value(accountDomain::accountId, accountId)
				.value(accountDomain::domainId, domain.toLowerCase())
				.value(accountDomain::createdAt, new Date())
				.value(accountDomain::lastStatus, DomainVerificationStatus.ACCEPTED)
				.future();
		
	}
	
	default Future<ResultSet> dropAccountDomain(String accountId, String domain) {
		
		return session()
				.delete()
				.where(accountDomain::accountId, eq(accountId))
				.and(accountDomain::domainId, eq(domain.toLowerCase()))
				.future();
		
	}
	
}
