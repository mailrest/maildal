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

import com.noorq.casser.mapping.OrderingDirection;
import com.noorq.casser.mapping.annotation.ClusteringColumn;
import com.noorq.casser.mapping.annotation.Constraints;
import com.noorq.casser.mapping.annotation.PartitionKey;
import com.noorq.casser.mapping.annotation.Table;

/**
 *   AccountLog is using to track changes in @Account that are made by particular @User
 *
 *   All those changes will be shown in UI dashboard as a news feed for the @User who manages @Account
 *   
 *   This will help customer to understand what his colleagues are doing in the system
 */

@Table
public interface AccountLog {

	/**
	 *  Corresponds to a specific @Account 
	 *  
	 *  @return account id
	 */
	
	@PartitionKey
	@IdConstraints.AccountId
	String accountId();

	/**
	 *  Time of the event in the log, by default sorted in decrement to retrieve 
	 *  and show last N events in the page
	 *  
	 *  @return immutable event timestamp
	 */
	
	@Constraints.NotNull
	@ClusteringColumn(ordering=OrderingDirection.DESC)
	Date eventAt();
	
	/**
	 *  Corresponds to a specific @User that made a change
	 *  
	 *  @return user info
	 */
	
	@Constraints.NotEmpty
	UserInfo userInfo();
	
	/**
	 *  Hard coded action types. We have limited actions and will have limited 
	 *  them forever, it is not a social network
	 *  
	 *  @return type of the logged action
	 */
	
	@Constraints.NotNull
	AccountAction action();

	/**
	 *  Some actions are made on specific @Template, so we are storing templateId to show in UI 
	 *  
	 *  @return template id
	 */
	
	@IdConstraints.TemplateId
	String templateId();
	
	/**
	 *  Some actions are made on specific @Domain, so we are storing domainId to show in UI
	 *  
	 *   @return domain id
	 */
	
	@IdConstraints.DomainId
	String domainId();
	
}
