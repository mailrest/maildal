/*
 *      Copyright (C) 2015 Noorq, Inc.
 *      All rights reserved.
 */
package com.mailrest.maildal.model;

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
	
	@PartitionKey
	String cookieId();
	
	String accountId();

}
