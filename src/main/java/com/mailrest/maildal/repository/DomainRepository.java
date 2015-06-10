/*
 *      Copyright (C) 2015 Noorq, Inc.
 *      All rights reserved.
 */
package com.mailrest.maildal.repository;

import static com.noorq.casser.core.Query.eq;

import java.util.Date;
import java.util.Optional;

import scala.concurrent.Future;

import com.datastax.driver.core.ResultSet;
import com.mailrest.maildal.gen.Generators;
import com.mailrest.maildal.model.Domain;
import com.noorq.casser.core.Casser;
import com.noorq.casser.support.Fun;

public interface DomainRepository extends AbstractRepository {

	static final Domain domain = Casser.dsl(Domain.class);
	
	default Future<Fun.Tuple2<ResultSet, String>> addDomain(String domainId, String domainIdn, String accountId) {
		
		String apiKey = Generators.API_KEY.next();
		
		return session()
				.upsert()
				.value(domain::domainId, domainId)
				.value(domain::domainIdn, domainIdn)
				.value(domain::createdAt, new Date())
				.value(domain::accountId, accountId)
				.value(domain::apiKey, apiKey)
				.future(apiKey);
		
	}
	
	default Future<Optional<Fun.Tuple1<String>>> findApiKey(String domainId, String accountId) {
		
		return session()
				.select(domain::apiKey)
				.where(domain::domainId, eq(domainId))
				.and(domain::accountId, eq(accountId))
				.single()
				.future();
	}
	
	default Future<Fun.Tuple2<ResultSet, String>> updateApiKey(String domainId, String accountId) {
		
		String apiKey = Generators.API_KEY.next();
		
		return session()
				.update()
				.set(domain::apiKey, apiKey)
				.where(domain::domainId, eq(domainId))
				.and(domain::accountId, eq(accountId))
				.future(apiKey);
		
	}
	
}
