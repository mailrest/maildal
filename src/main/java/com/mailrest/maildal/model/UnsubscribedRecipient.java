/*
 *      Copyright (C) 2015 Noorq, Inc.
 *      All rights reserved.
 */
package com.mailrest.maildal.model;

import java.util.Date;

import com.noorq.casser.mapping.annotation.ClusteringColumn;
import com.noorq.casser.mapping.annotation.Constraints;
import com.noorq.casser.mapping.annotation.PartitionKey;
import com.noorq.casser.mapping.annotation.Table;

@Table
public interface UnsubscribedRecipient {

	@Constraints.NotEmpty
	@Constraints.LowerCase
	@PartitionKey(ordinal=0)
	String domainId();

	@Constraints.Number
	@Constraints.NotEmpty
	@PartitionKey(ordinal=1)
	String accountId();

	@Constraints.NotEmpty
	@Constraints.Email
	@Constraints.LowerCase
	@ClusteringColumn
	String email();
	
	@Constraints.NotNull
	Date unsubscribedAt();
	
}
