/*
 *      Copyright (C) 2015 Noorq, Inc.
 *      All rights reserved.
 */
package com.mailrest.maildal.model;

import com.mailrest.maildal.model.constraint.UserId;
import com.noorq.casser.mapping.annotation.Constraints;
import com.noorq.casser.mapping.annotation.UDT;

/**
 *  AccountUser is a internal type, that is User Defined Type, used to store
 *  user details in @Account object itself
 *  
 *  Very helpful to retrieve all users that are linked to a specific Account
 *  
 *  Used as a secondary index and primary storage of the list of users
 *
 */

@UDT
public interface AccountUser {
	
	/**
	 *  UserId is the identity of the user
	 *  
	 *  By default is can be lower case of IDN.toASCII email of the @User 
	 *   
	 */
	
	@UserId
	String userId();

	/**
	 *  Actual email of the user as it is
	 *  
	 *  Used to retrieve password and to confirm user, for specific notification
	 */
	
	@Constraints.NotEmpty
	@Constraints.Email
	String email();

	/**
	 *  Optional field that specify team of the user
	 *  
	 *  Can be used for additional grouping in UI
	 */
	
	String team();
	
	/**
	 *  First name of the user name 
	 */
	
	String firstName();

	/**
	 *  Last name of the user name 
	 */

	String lastName();
	
	/**
	 *  Permission of the @User
	 *  
	 *  User can have ADMIN, WRITE and READ_ONLY rights
	 *  
	 *  ADMIN - user can add/remove other users, can see all content of the organization, no limits
	 *  WRITE - user can change settings, pay bills, add/remove boxes, add/remove templates
	 *  READ_ONLY - user can only read account and user information that have to be public in organization
	 *   
	 */
	
	@Constraints.NotNull
	UserPermission permission();
	
	/**
	 *  In the time of user creation this flag is false
	 *  
	 *  After confirming email this flag became true and record in @User tables creates after that
	 */
	
	boolean confirmed();
	
}
