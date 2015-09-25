/*
 *      Copyright (C) 2015 Noorq, Inc.
 *
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
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
