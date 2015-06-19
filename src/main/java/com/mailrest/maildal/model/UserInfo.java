/*
 *      Copyright (C) 2015 Noorq, Inc.
 *      All rights reserved.
 */
package com.mailrest.maildal.model;

import com.mailrest.maildal.model.constraint.UserId;
import com.noorq.casser.mapping.annotation.Constraints;
import com.noorq.casser.mapping.annotation.UDT;

/**
 *  UserInfo is using to store user related information in AccountLog
 *  
 *  Usually all actions are user initiated actions, so this structure have to be always filled
 *
 */

@UDT
public interface UserInfo {
	
	/**
	 *  Corresponds to a particular User 
	 */
	
	@UserId
	String userId();
	
	/**
	 *  IP address of the request 
	 */
	
	@Constraints.NotEmpty
	String ip();

	/**
	 *  UserAgent of the request if has
	 */

	String userAgent();
	
}