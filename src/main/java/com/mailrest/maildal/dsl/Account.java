/*
 *      Copyright (C) 2015 Noorq, Inc.
 *      All rights reserved.
 */
package com.mailrest.maildal.dsl;

import java.util.Date;
import java.util.List;

import com.noorq.casser.mapping.annotation.PartitionKey;
import com.noorq.casser.mapping.annotation.Table;

@Table
public interface Account {

	/*
	 * Randomly generated AccountId like this 1234567890
	 */
	
	@PartitionKey
	String accountId();
	
	Date createdAt();
	
	/*
	 * lower-case email
	 */
	
	String email();
	
	String firstName();

	String lastName();
	
	String organization();
	
	String timezone();
	
	List<String> domains();
	
}
