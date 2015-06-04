/*
 *      Copyright (C) 2015 Noorq, Inc.
 *      All rights reserved.
 */
package com.mailrest.maildal.repository;

import scala.concurrent.Future;

import com.datastax.driver.core.ResultSet;
import com.mailrest.maildal.model.User;
import com.mailrest.maildal.secur.PasswordHash;
import com.noorq.casser.core.Casser;
import static com.noorq.casser.core.Query.*;

public interface UserRepository extends AbstractRepository {
	
	static final User user = Casser.dsl(User.class);

	default Future<ResultSet> storeUser(
			String email,
			String password, 
			String accountId) {
		
		String passwordHash = PasswordHash.calculate(password);
		
		return session()
			.upsert()
			.value(user::email, email)
			.value(user::passwordHash, passwordHash)
			.value(user::accountId, accountId)
			.future();
		
	}
	
	default Future<ResultSet> dropUser(
			String email) {
		
		return session()
			.delete()
			.where(user::email, eq(email))
			.future();
		
	}
	
}
