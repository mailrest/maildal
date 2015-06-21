/*
 *      Copyright (C) 2015 Noorq, Inc.
 *      All rights reserved.
 */
package com.mailrest.maildal.model;

import java.util.Date;

import com.mailrest.maildal.model.constraint.AccountId;
import com.mailrest.maildal.model.constraint.DomainId;
import com.mailrest.maildal.model.constraint.TemplateId;
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
	 */
	
	@PartitionKey
	@AccountId
	String accountId();

	/**
	 *  Time of the event in the log, by default sorted in decrement to retrieve 
	 *  and show last N events in the page
	 */
	
	@Constraints.NotNull
	@ClusteringColumn(ordering=OrderingDirection.DESC)
	Date eventAt();
	
	/**
	 *  Corresponds to a specific @User that made a change
	 */
	
	@Constraints.NotEmpty
	UserInfo userInfo();
	
	/**
	 *  Hard coded action types. We have limited actions and will have limited 
	 *  them forever, it is not a social network
	 */
	
	@Constraints.NotNull
	AccountAction action();

	/**
	 *  Some actions are made on specific @Template, so we are storing templateId to show in UI 
	 */
	
	@TemplateId
	String templateId();
	
	/**
	 *  Some actions are made on specific @Domain, so we are storing domainId to show in UI 
	 */
	
	@DomainId
	String domainId();
	
}
