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

@Table
public interface User {

	@PartitionKey
	@UserId
	String userId();

	@Constraints.NotEmpty
	String passwordHash();

	@AccountId
	String accountId();
	
	@Constraints.NotNull
	UserPermission permission();

}
