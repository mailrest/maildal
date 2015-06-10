/*
 *      Copyright (C) 2015 Noorq, Inc.
 *      All rights reserved.
 */
package com.mailrest.maildal.model;

import java.util.Date;
import java.util.Map;

import com.noorq.casser.mapping.annotation.Constraints;
import com.noorq.casser.mapping.annotation.PartitionKey;
import com.noorq.casser.mapping.annotation.Table;

@Table
public interface Account {

	/*
	 * Randomly generated AccountId, like this 1234567890
	 */
	
	@Constraints.Number
	@Constraints.NotEmpty
	@PartitionKey
	String accountId();
	
	@Constraints.NotNull
	Date createdAt();
	
	@Constraints.NotEmpty
	String organization();
	
	String team();
	
	String timezone();

    /*
     * Key is the userId
     */
	
	Map<String, AccountUser> users();
	
}
