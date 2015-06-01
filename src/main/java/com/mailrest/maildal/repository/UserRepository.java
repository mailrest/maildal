/*
 *      Copyright (C) 2015 Noorq, Inc.
 *      All rights reserved.
 */
package com.mailrest.maildal.repository;

import com.datastax.driver.core.ResultSet;
import com.google.common.util.concurrent.ListenableFuture;
import com.mailrest.maildal.model.User;
import com.noorq.casser.core.Casser;

public interface UserRepository extends AbstractRepository {
	
	static final User user = Casser.dsl(User.class);

	default ListenableFuture<ResultSet> createUser(
			String email, 
			String password, 
			String accountId) {
		
		return session()
		.insert()
		.value(user::email, email)
		.value(user::password, password)
		.value(user::accountId, accountId)
		.async();
		
	}
}
