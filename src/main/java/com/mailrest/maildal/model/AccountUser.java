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
	 *  @return user id 
	 */
	
	@IdConstraints.UserId
	String userId();

	/**
	 *  Actual email of the user as it is
	 *  
	 *  Used to retrieve password and to confirm user, for specific notification
	 *  
	 *  @return email that entered by user as it is
	 */
	
	@Constraints.NotEmpty
	@Constraints.Email
	String email();

	/**
	 *  Optional field that specify team of the user
	 *  
	 *  Can be used for additional grouping in UI
	 *  
	 *  @return user's team name is exists
	 */
	
	String team();
	
	/**
	 *  First name of the user name 
	 *  
	 *  @return first name of the user if exists
	 */
	
	String firstName();

	/**
	 *  Last name of the user name 
	 *  
	 *  @return last name of the user if exists
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
	 *  @return user permission type
	 *   
	 */
	
	@Constraints.NotNull
	UserPermission permission();
	
	/**
	 *  In the time of user creation this flag is false
	 *  
	 *  After confirming email this flag became true and record in @User tables creates after that
	 *  
	 *  @return true is user email address was confirmed
	 */
	
	boolean confirmed();
	
}
