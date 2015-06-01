/*
 *      Copyright (C) 2015 Noorq, Inc.
 *      All rights reserved.
 */
package com.mailrest.maildal.dsl;

import com.noorq.casser.mapping.annotation.PartitionKey;
import com.noorq.casser.mapping.annotation.Table;

/*
 * Table with TTL 1 Day
 */

@Table
public interface UserLink {

	/*
	 * Randomly generated linkId 
	 */
	
	@PartitionKey
	String linkId();
	
	CallbackAction action();
	
	String email();
	
}
