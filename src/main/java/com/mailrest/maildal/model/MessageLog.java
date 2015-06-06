package com.mailrest.maildal.model;

import java.util.Date;
import java.util.UUID;

import com.noorq.casser.mapping.OrderingDirection;
import com.noorq.casser.mapping.annotation.ClusteringColumn;
import com.noorq.casser.mapping.annotation.PartitionKey;
import com.noorq.casser.mapping.annotation.Table;
import com.noorq.casser.mapping.annotation.Types;


/**
 *   Use TTL 60 Days
 */

@Table
public interface MessageLog {

	@PartitionKey(ordinal=0)
	String accountId();
	
	/*
	 * Lower-case domain
	 */
	
	@PartitionKey(ordinal=1)
	String domain();
	
	@PartitionKey(ordinal=2)
	Date dayAt();

	@Types.Timeuuid
	@ClusteringColumn(ordering = OrderingDirection.DESC)
	UUID eventAt();
	
	String messageId();
	
	String publicId();
	
	MessageAction action();

	MessageDelivery delivery();

	MessageSender sender();
	
	MessageRecipient recipient();
	
	MessageHeaders headers();
	
}
