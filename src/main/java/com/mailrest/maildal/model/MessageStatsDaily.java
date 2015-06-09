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
import com.noorq.casser.mapping.annotation.Types;

@Table
public interface MessageStatsDaily {

	@Constraints.NotEmpty
	@PartitionKey(ordinal=0)
	String accountId();
	
	@Constraints.NotEmpty
	@Constraints.LowerCase
	@PartitionKey(ordinal=1)
	String domainId();
	
	@Constraints.NotNull
	@ClusteringColumn(ordering=OrderingDirection.DESC)
	Date dayAt();
	
	/*
	 * Incoming
	 */
	
	@Types.Counter
	long received();
	
	/*
	 * Outgoing
	 */
	
	@Types.Counter
	long delivered();

	@Types.Counter
	long dropped();
	
	/*
	
	@Types.Counter
	long opens();

	@Types.Counter
	long clicks();

	@Types.Counter
	long bounces();

	@Types.Counter
	long spamReports();
	
	*/

	@Types.Counter
	long unsubscribed();

}
