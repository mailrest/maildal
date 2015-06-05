/*
 *      Copyright (C) 2015 Noorq, Inc.
 *      All rights reserved.
 */
package com.mailrest.maildal.repository;

import static com.noorq.casser.core.Query.eq;

import java.util.Optional;

import scala.concurrent.Future;

import com.datastax.driver.core.ResultSet;
import com.mailrest.maildal.model.User;
import com.mailrest.maildal.secur.PasswordHash;
import com.noorq.casser.core.Casser;

public interface UserRepository extends AbstractRepository {
	
	static final User user = Casser.dsl(User.class);

	default Future<Optional<User>> findUser(String email) {

		return session()
			.select(User.class)
			.where(user::email, eq(email.toLowerCase()))
			.single()
			.future();
		
	}
	
	default Future<ResultSet> saveUser(
			String email,
			String password, 
			String accountId) {
		
		String passwordHash = PasswordHash.calculate(password);
		
		return session()
			.upsert()
			.value(user::email, email.toLowerCase())
			.value(user::passwordHash, passwordHash)
			.value(user::accountId, accountId)
			.future();
		
	}
	
	default Future<ResultSet> dropUser(
			String email) {
		
		return session()
			.delete()
			.where(user::email, eq(email.toLowerCase()))
			.future();
		
	}
	
}
