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
public interface DomainOwner {

	@Constraints.NotEmpty
	@Constraints.LowerCase
	@PartitionKey
	String domainId();
	
	@Constraints.NotNull
	@ClusteringColumn(ordering=OrderingDirection.DESC)
	Date verifiedAt();

	@Constraints.Number
	@Constraints.NotEmpty
	String accountId();
	
}
