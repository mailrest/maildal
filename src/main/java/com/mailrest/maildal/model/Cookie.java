/*
 *      Copyright (C) 2015 Noorq, Inc.
 *      All rights reserved.
 */
package com.mailrest.maildal.model;

import com.mailrest.maildal.model.constraint.AccountId;
import com.noorq.casser.mapping.annotation.Constraints;
import com.noorq.casser.mapping.annotation.PartitionKey;
import com.noorq.casser.mapping.annotation.Table;

/*
 * Table with TTL 30 Days
 */

@Table
public interface Cookie {

	/*
	 * Randomly generated String for Cookie
	 */
	
	@Constraints.NotEmpty
	@PartitionKey
	String cookieId();
	
	@AccountId
	String accountId();

}
