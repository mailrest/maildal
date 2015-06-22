/*
 *      Copyright (C) 2015 Noorq, Inc.
 *      All rights reserved.
 */
package com.mailrest.maildal.model;

import com.mailrest.maildal.model.constraint.AccountId;
import com.mailrest.maildal.model.constraint.UserId;
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
	@UserId
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
	
	@AccountId
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
