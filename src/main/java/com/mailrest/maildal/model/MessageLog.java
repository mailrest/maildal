/*
 *      Copyright (C) 2015 Noorq, Inc.
 *      All rights reserved.
 */
package com.mailrest.maildal.model;

import java.util.Date;
import java.util.UUID;

import com.mailrest.maildal.model.constraint.AccountId;
import com.mailrest.maildal.model.constraint.DomainId;
import com.mailrest.maildal.model.constraint.MessageId;
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

	@PartitionKey(ordinal=0)
	@AccountId
	String accountId();
	
	@PartitionKey(ordinal=1)
	@DomainId
	String domainId();
	
	@Constraints.NotNull
	@PartitionKey(ordinal=2)
	Date dayAt();

	@Constraints.NotNull
	@Types.Timeuuid
	@ClusteringColumn(ordering = OrderingDirection.DESC)
	UUID eventAt();
	
	@MessageId
	String messageId();
	
	String publicId();
	
	@Constraints.NotNull
	MessageAction action();

	MessageDelivery delivery();

	MessageSender sender();
	
	MessageRecipient recipient();
	
	MessageHeaders headers();
	
}
