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
 *   User table is using to create secondary index around UserId -> AccountId
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
	 */
	
	@PartitionKey
	@UserId
	String userId();

	/**
	 *  We are not storing password itself, we are storing only hash of the password 
	 */
	
	@Constraints.NotEmpty
	String passwordHash();

	/**
	 *  Account under which user as created,
	 *  all permissions based per account 
	 */
	
	@AccountId
	String accountId();
	
	/**
	 *  Permission of the user, used for authorization purpose
	 *  
	 *  User can have ADMIN, WRITE and READ_ONLY permissions that vary on access type in REST API and UI 
	 *   
	 */
	
	@Constraints.NotNull
	UserPermission permission();

}
