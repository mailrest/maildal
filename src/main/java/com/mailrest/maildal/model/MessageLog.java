/*
 *      Copyright (C) 2015 Noorq, Inc.
 *      All rights reserved.
 */
package com.mailrest.maildal.model;

import java.util.Date;
import java.util.UUID;

import com.noorq.casser.mapping.OrderingDirection;
import com.noorq.casser.mapping.annotation.ClusteringColumn;
import com.noorq.casser.mapping.annotation.Constraints;
import com.noorq.casser.mapping.annotation.PartitionKey;
import com.noorq.casser.mapping.annotation.Table;
import com.noorq.casser.mapping.annotation.Types;


/**
 *   Use TTL 60 Days
 */

@Table
public interface MessageLog {

	@Constraints.Number
	@Constraints.NotEmpty
	@PartitionKey(ordinal=0)
	String accountId();
	
	@Constraints.NotEmpty
	@Constraints.LowerCase
	@PartitionKey(ordinal=1)
	String domainId();
	
	@Constraints.NotNull
	@PartitionKey(ordinal=2)
	Date dayAt();

	@Constraints.NotNull
	@Types.Timeuuid
	@ClusteringColumn(ordering = OrderingDirection.DESC)
	UUID eventAt();
	
	@Constraints.NotEmpty
	String messageId();
	
	String publicId();
	
	@Constraints.NotNull
	MessageAction action();

	MessageDelivery delivery();

	MessageSender sender();
	
	MessageRecipient recipient();
	
	MessageHeaders headers();
	
}
