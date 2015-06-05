/*
 *      Copyright (C) 2015 Noorq, Inc.
 *      All rights reserved.
 */
package com.mailrest.maildal.model;

import java.util.Date;

import com.noorq.casser.mapping.OrderingDirection;
import com.noorq.casser.mapping.annotation.ClusteringColumn;
import com.noorq.casser.mapping.annotation.PartitionKey;
import com.noorq.casser.mapping.annotation.Table;

@Table
public interface AccountLog {

	@PartitionKey
	String accountId();

	@ClusteringColumn(ordering=OrderingDirection.DESC)
	Date eventAt();
	
	AccountAction action();
	
	String email();
	
	String ip();

	String userAgent();

	String template();
	
	String domain();
	
}
