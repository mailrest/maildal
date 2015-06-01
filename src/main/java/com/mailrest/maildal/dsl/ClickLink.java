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
public interface ClickLink {

	/*
	 * Randomly generated linkId (can be UUID)
	 */
	
	@PartitionKey
	String linkId();
	
	LinkType action();
	
	String email();
	
}
