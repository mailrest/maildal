/*
 *      Copyright (C) 2015 Noorq, Inc.
 *      All rights reserved.
 */
package com.mailrest.maildal.model;

import com.noorq.casser.mapping.annotation.Constraints;
import com.noorq.casser.mapping.annotation.PartitionKey;
import com.noorq.casser.mapping.annotation.Table;

@Table
public interface User {

	@Constraints.NotEmpty
	@Constraints.LowerCase
	@PartitionKey
	String userId();

	@Constraints.NotEmpty
	String passwordHash();

	@Constraints.Number
	@Constraints.NotEmpty
	String accountId();
	
	@Constraints.NotNull
	UserPermission permission();

}
