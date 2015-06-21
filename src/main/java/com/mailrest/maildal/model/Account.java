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

/**
 *  This is the root element in the Model
 *  
 *  Most of other entities are related to particular Account
 *  
 *  Account is using for billing purposes, finally all transactions
 *  will be calculated per account
 *  
 *  Customer of the MailRest service always has account
 */

@Table
public interface Account {

	/**
	 * Randomly generated AccountId, like this 1234567890
	 * 
	 * @return account id 
	 */
	
	@PartitionKey
	@AccountId
	String accountId();
	
	/**
	 * Immutable creation time
	 * 
	 * @return timestamp when account created
	 */
	
	@Constraints.NotNull
	Date createdAt();
	
	/**
	 *  Customer's business name, organization, startup, group of people 
	 *  
	 *  @return business name
	 */
	
	@Constraints.NotEmpty
	String businessName();
	
	/**
	 *  Timezone of the account, using for daily stats and logs
	 *  
	 *  @return  timezone name in java
	 */
	
	@Constraints.NotEmpty
	String timezone();

    /**
     *  Users of the account that can login and operate of the account.
     *  Usually users are managers or owners of the organization
     * 
     *  Key is the userId
     *  
     *  @return internal map of the users serialized in single field
     */
	
	Map<String, AccountUser> users();
	
}
