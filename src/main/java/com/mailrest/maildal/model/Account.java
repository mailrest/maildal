/*
 *      Copyright (C) 2015 Noorq, Inc.
 *      All rights reserved.
 */
package com.mailrest.maildal.model;

import java.util.Date;
import java.util.Map;

import com.mailrest.maildal.model.constraint.AccountId;
import com.noorq.casser.mapping.annotation.Constraints;
import com.noorq.casser.mapping.annotation.PartitionKey;
import com.noorq.casser.mapping.annotation.Table;

@Table
public interface Account {

	/*
	 * Randomly generated AccountId, like this 1234567890
	 */
	
	@PartitionKey
	@AccountId
	String accountId();
	
	@Constraints.NotNull
	Date createdAt();
	
	@Constraints.NotEmpty
	String businessName();
	
	String timezone();

    /*
     * Key is the userId
     */
	
	Map<String, AccountUser> users();
	
}
