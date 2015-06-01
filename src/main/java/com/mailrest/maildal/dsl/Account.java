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
	 * Normalized to lowercase email
	 */
	
	String email();
	
	/*
	 * Randomly generated ApiKey like this EYgbQpG0Q9uM9ND5ViJArw
	 */
	
	String apiKey();
	
	String firstName();

	String lastName();
	
	String organization();
	
	String timezone();
	
	List<String> domains();
	
}
