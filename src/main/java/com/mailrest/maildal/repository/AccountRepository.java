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
import com.mailrest.maildal.gen.AccountId;
import com.mailrest.maildal.model.Account;
import com.noorq.casser.core.Casser;
import com.noorq.casser.support.Fun;

public interface AccountRepository extends AbstractRepository {

	static final Account account = Casser.dsl(Account.class);
	
	static final String DEFAULT_TIMEZONE = "America/Los_Angeles";
	
	default Future<Optional<Account>> findAccount(String accountId) {
		
		return session()
				.select(Account.class)
				.where(account::accountId, eq(accountId))
				.single()
				.future();
		
	}
	
	default Future<Fun.Tuple2<ResultSet, String>> createAccount(
			String email, 
			String firstName, 
			String lastName, 
			String organization,
			String timezone) {
		
		String accountId = AccountId.next();
		
		return session()
			.insert()
			.value(account::accountId, accountId)
			.value(account::createdAt, new Date())
			.value(account::email, email.toLowerCase())
			.value(account::firstName, firstName)
			.value(account::lastName, lastName)
			.value(account::organization, organization)
			.value(account::timezone, timezone.isEmpty() ? DEFAULT_TIMEZONE : timezone)
			.future(accountId);
		
	}
	
	default Future<ResultSet> dropAccount(String accountId) {
		
		return session()
				.delete()
				.where(account::accountId, eq(accountId))
				.future();
		
	}
	

}
