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

@Table
public interface AccountLog {

	@PartitionKey
	@AccountId
	String accountId();

	@Constraints.NotNull
	@ClusteringColumn(ordering=OrderingDirection.DESC)
	Date eventAt();
	
	@Constraints.NotEmpty
	UserInfo userInfo();
	
	@Constraints.NotNull
	AccountAction action();

	@TemplateId
	String templateId();
	
	@DomainId
	String domainId();
	
}
