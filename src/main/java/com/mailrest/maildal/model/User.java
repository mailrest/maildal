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
package com.mailrest.maildal.model;

import com.noorq.casser.mapping.annotation.Constraints;
import com.noorq.casser.mapping.annotation.PartitionKey;
import com.noorq.casser.mapping.annotation.Table;

/**
 *   User table is using to create secondary index around UserId -&gt; AccountId
 *  
 *   that is using for authentication purpose
 *   
 *   Also we are storing permission field that is using for authorization purpose of the
 *   user's actions in the account
 *
 */

@Table
public interface User {

	/**
	 *  UserId is the login and primary key of this table
	 *  
	 *  @return user id
	 */
	
	@PartitionKey
	@IdConstraints.UserId
	String userId();

	/**
	 *  We are not storing password itself, we are storing only hash of the password 
	 *  
	 *  @return password hash
	 */
	
	@Constraints.NotEmpty
	String passwordHash();

	/**
	 *  Account under which user as created,
	 *  all permissions based per account 
	 *  
	 *  @return account id
	 */
	
	@IdConstraints.AccountId
	String accountId();
	
	/**
	 *  Permission of the user, used for authorization purpose
	 *  
	 *  User can have ADMIN, WRITE and READ_ONLY permissions that vary on access type in REST API and UI 
	 *  
	 *  @return user permission type
	 *   
	 */
	
	@Constraints.NotNull
	UserPermission permission();

}
