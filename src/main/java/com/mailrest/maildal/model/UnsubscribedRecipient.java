/*
 *      Copyright (C) 2015 Noorq, Inc.
 *      All rights reserved.
 */
package com.mailrest.maildal.model;

import java.util.Date;

import com.noorq.casser.mapping.annotation.ClusteringColumn;
import com.noorq.casser.mapping.annotation.PartitionKey;
import com.noorq.casser.mapping.annotation.Table;

@Table
public interface UnsubscribedRecipient {

	/*
	 * lower-case domain name
	 */
	
	@PartitionKey(ordinal=0)
	String domain();

	@PartitionKey(ordinal=1)
	String accountId();

	/*
	 * Lower-case email
	 */
	
	@ClusteringColumn
	String email();
	
	Date unsubscribedAt();
	
}
