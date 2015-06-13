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
import com.google.common.collect.ImmutableMap;
import com.mailrest.maildal.gen.Generators;
import com.mailrest.maildal.model.Account;
import com.mailrest.maildal.model.AccountUser;
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
			AccountUser user,
			String organization,
			String team,
			String timezone) {
		
		String accountId = Generators.ACCOUNT_ID.next();

		if (timezone == null || timezone.isEmpty()) {
			timezone = DEFAULT_TIMEZONE;
		}
		
		return session()
			.insert()
			.value(account::accountId, accountId)
			.value(account::createdAt, new Date())
			.value(account::organization, organization)
			.value(account::team, team)
			.value(account::timezone, timezone)
			.value(account::users, ImmutableMap.of(user.userId(), user))
			.future(accountId);
		
	}
	
	default Future<Optional<AccountUser>> findAccountUser(String accountId, String userId) {
		
		return session()
				.select(account::users)
				.where(account::accountId, eq(accountId))
				.single()
				.map(t -> t._1.get(userId))
				.future();
		
	}
	
	default Future<ResultSet> putAccountUser(String accountId, String userId, AccountUser user) {
		
		return session()
				.update()
				.put(account::users, userId, user)
				.where(account::accountId, eq(accountId))
				.future();
		
	}
	
	default Future<ResultSet> removeAccountUser(String accountId, String userId) {
		
		return session()
				.update()
				.put(account::users, userId, null)
				.where(account::accountId, eq(accountId))
				.future();
		
	}
	
	default Future<ResultSet> dropAccount(String accountId) {
		
		return session()
				.delete()
				.where(account::accountId, eq(accountId))
				.future();
		
	}
	

}
