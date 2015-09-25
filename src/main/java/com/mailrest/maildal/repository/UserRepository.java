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
import scala.Option;
import scala.concurrent.Future;

import com.datastax.driver.core.ResultSet;
import com.mailrest.maildal.model.User;
import com.mailrest.maildal.model.UserPermission;
import com.mailrest.maildal.secur.PasswordHash;
import com.noorq.casser.core.Casser;

public interface UserRepository extends AbstractRepository {
	
	static final User user = Casser.dsl(User.class);

	default Future<Option<User>> findUser(String userId) {

		return session()
			.select(User.class)
			.where(user::userId, eq(userId))
			.single()
			.future();
		
	}
	
	default Future<ResultSet> saveNewUser(
			String userId,
			String password, 
			String accountId,
			UserPermission permission) {
		
		String passwordHash = PasswordHash.INSTANCE.calculate(password);
		
		return session()
			.insert()
			.value(user::userId, userId)
			.value(user::passwordHash, passwordHash)
			.value(user::accountId, accountId)
			.value(user::permission, permission)
			.future();
		
	}
	
	default Future<ResultSet> updatePassword(String userId, String password) {

		String passwordHash = PasswordHash.INSTANCE.calculate(password);
		
		return session()
			.update()
			.set(user::passwordHash, passwordHash)
			.where(user::userId, eq(userId))
			.future();
	}
	
	default Future<ResultSet> dropUser(
			String userId) {
		
		return session()
			.delete()
			.where(user::userId, eq(userId))
			.future();
		
	}
	
}
