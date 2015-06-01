/*
 *      Copyright (C) 2015 Noorq, Inc.
 *      All rights reserved.
 */
package com.mailrest.maildal.repository;

import com.datastax.driver.core.ResultSet;
import com.google.common.base.Function;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.mailrest.maildal.idgen.AccountId;
import com.mailrest.maildal.model.Account;
import com.noorq.casser.core.Casser;

public interface AccountRepository extends AbstractRepository {

	static final Account account = Casser.dsl(Account.class);
	
	default ListenableFuture<String> createAccount(
			String email, 
			String firstName, 
			String lastName, 
			String organization) {
		
		String accountId = AccountId.next();
		
		ListenableFuture<ResultSet> rsFuture = session()
		.insert()
		.value(account::accountId, accountId)
		.value(account::email, email)
		.value(account::firstName, firstName)
		.value(account::lastName, lastName)
		.value(account::organization, organization)
		.async();
		
		return Futures.transform(rsFuture, new Function<ResultSet, String>() {

			@Override
			public String apply(ResultSet input) {
				return accountId;
			}
			
		});
		
	}
	
}
