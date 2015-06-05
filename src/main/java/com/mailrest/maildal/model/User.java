/*
 *      Copyright (C) 2015 Noorq, Inc.
 *      All rights reserved.
 */
package com.mailrest.maildal.model;

import com.noorq.casser.mapping.annotation.PartitionKey;
import com.noorq.casser.mapping.annotation.Table;

@Table
public interface User {

	/*
	 * lower-case email
	 */
	
	@PartitionKey
	String email();

	String passwordHash();

	String accountId();

}
