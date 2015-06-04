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
public interface DomainOwner {

	/*
	 * Lower-case domain name, for example lt.su
	 */
	
	@PartitionKey
	String domain();
	
	@ClusteringColumn(ordering=OrderingDirection.DESC)
	Date verifiedAt();

	String accountId();

	String requestId();
	
}
