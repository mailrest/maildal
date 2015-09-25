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

import static com.noorq.casser.core.Query.eq;

import java.util.Date;

import scala.Option;
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
	
	default Future<Option<Account>> findAccount(String accountId) {
		
		return session()
				.select(Account.class)
				.where(account::accountId, eq(accountId))
				.single()
				.future();
		
	}
	
	default Future<Fun.Tuple2<ResultSet, String>> createAccount(
			AccountUser user,
			String businessName,
			String timezone) {
		
		String accountId = Generators.ACCOUNT_ID.next();

		if (timezone == null || timezone.isEmpty()) {
			timezone = DEFAULT_TIMEZONE;
		}
		
		return session()
			.insert()
			.value(account::accountId, accountId)
			.value(account::createdAt, new Date())
			.value(account::businessName, businessName)
			.value(account::timezone, timezone)
			.value(account::users, ImmutableMap.of(user.userId(), user))
			.future(accountId);
		
	}
	
	default Future<Option<AccountUser>> findAccountUser(UserRef userRef) {
		
		return session()
				.select(account::users)
				.where(account::accountId, eq(userRef.accountId()))
				.single()
				.map(t -> t._1.get(userRef.userId()))
				.future();
		
	}
	
	default Future<ResultSet> putAccountUser(String accountId, AccountUser user) {
		
		return session()
				.update()
				.put(account::users, user.userId(), user)
				.where(account::accountId, eq(accountId))
				.future();
		
	}
	
	default Future<ResultSet> removeAccountUser(UserRef userRef) {
		
		return session()
				.update()
				.put(account::users, userRef.userId(), null)
				.where(account::accountId, eq(userRef.accountId()))
				.future();
		
	}
	
	default Future<ResultSet> dropAccount(String accountId) {
		
		return session()
				.delete()
				.where(account::accountId, eq(accountId))
				.future();
		
	}
	

}
