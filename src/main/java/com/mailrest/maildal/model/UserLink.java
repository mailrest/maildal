/*
 *      Copyright (C) 2015 Noorq, Inc.
 *      All rights reserved.
 */
package com.mailrest.maildal.model;

import com.noorq.casser.mapping.annotation.Constraints;
import com.noorq.casser.mapping.annotation.PartitionKey;
import com.noorq.casser.mapping.annotation.Table;

/*
 * Table with TTL 1 Day
 */

@Table
public interface UserLink {

	@Constraints.NotEmpty
	@PartitionKey
	String linkId();
	
	@Constraints.NotNull
	CallbackAction action();
	
	@Constraints.NotEmpty
	String accountId();
	
	@Constraints.NotEmpty
	@Constraints.LowerCase
	String userId();
	
}
