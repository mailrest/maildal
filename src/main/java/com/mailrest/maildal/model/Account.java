/*
 *      Copyright (C) 2015 Noorq, Inc.
 *      All rights reserved.
 */
package com.mailrest.maildal.model;

import java.util.Date;
import java.util.Set;

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
	
	/*
	 * Add domain requests for verification
	 */
	
	Set<String> domainRequests();
	
	/*
	 * lower-case verified domains
	 */
	
	Set<String> domains();
	
}
