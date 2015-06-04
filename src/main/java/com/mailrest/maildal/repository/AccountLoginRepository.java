/*
 *      Copyright (C) 2015 Noorq, Inc.
 *      All rights reserved.
 */
package com.mailrest.maildal.repository;

import java.util.Date;
import java.util.stream.Stream;

import scala.concurrent.Future;

import static com.noorq.casser.core.Query.eq;

import com.datastax.driver.core.ResultSet;
import com.mailrest.maildal.model.AccountLogin;
import com.noorq.casser.core.Casser;

public interface AccountLoginRepository extends AbstractRepository {

	static final AccountLogin accountLogin = Casser.dsl(AccountLogin.class);
	
	default Future<Stream<AccountLogin>> getAccountLogins(String accountId, int max) {
		
		return session()
				.select(AccountLogin.class)
				.where(accountLogin::accountId, eq(accountId))
				.limit(max)
				.future();
		
	}
	
	default Future<ResultSet> addAccountLogin(String accountId, String ip, String userAgent) {
		
		return session()
				.upsert()
				.value(accountLogin::accountId, accountId)
				.value(accountLogin::loginAt, new Date())
				.value(accountLogin::ip, ip)
				.value(accountLogin::userAgent, userAgent)
				.future();
		
	}
	
}
