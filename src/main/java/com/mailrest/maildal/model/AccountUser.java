/*
 *      Copyright (C) 2015 Noorq, Inc.
 *      All rights reserved.
 */
package com.mailrest.maildal.model;

import com.noorq.casser.mapping.annotation.Constraints;
import com.noorq.casser.mapping.annotation.UDT;

@UDT
public interface AccountUser {
	
	@Constraints.NotEmpty
	@Constraints.LowerCase
	String userId();

	@Constraints.NotEmpty
	@Constraints.Email
	String email();

	String firstName();

	String lastName();
	
	@Constraints.NotNull
	UserPermission permission();
	
	boolean confirmed();
	
}
