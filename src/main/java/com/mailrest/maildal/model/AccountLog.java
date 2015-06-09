/*
 *      Copyright (C) 2015 Noorq, Inc.
 *      All rights reserved.
 */
package com.mailrest.maildal.model;

import java.util.Date;

import com.noorq.casser.mapping.OrderingDirection;
import com.noorq.casser.mapping.annotation.ClusteringColumn;
import com.noorq.casser.mapping.annotation.Constraints;
import com.noorq.casser.mapping.annotation.PartitionKey;
import com.noorq.casser.mapping.annotation.Table;

@Table
public interface AccountLog {

	@Constraints.NotEmpty
	@PartitionKey
	String accountId();

	@Constraints.NotNull
	@ClusteringColumn(ordering=OrderingDirection.DESC)
	Date eventAt();
	
	@Constraints.NotEmpty
	@Constraints.LowerCase
	String userId();
	
	@Constraints.NotNull
	AccountAction action();
	
	@Constraints.NotEmpty
	String ip();
	
	String userAgent();

	String template();
	
	String domainId();
	
}
