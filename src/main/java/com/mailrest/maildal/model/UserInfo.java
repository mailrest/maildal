/*
 *      Copyright (C) 2015 Noorq, Inc.
 *      All rights reserved.
 */
package com.mailrest.maildal.model;

import com.mailrest.maildal.model.constraint.UserId;
import com.noorq.casser.mapping.annotation.Constraints;
import com.noorq.casser.mapping.annotation.UDT;

@UDT
public interface UserInfo {
	
	@UserId
	String userId();
	
	@Constraints.NotEmpty
	String ip();
	
	String userAgent();
	
}