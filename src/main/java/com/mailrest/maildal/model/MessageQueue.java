/*
 *      Copyright (C) 2015 Noorq, Inc.
 *      All rights reserved.
 */
package com.mailrest.maildal.model;

import java.util.UUID;

import com.mailrest.maildal.model.constraint.MessageId;
import com.noorq.casser.mapping.OrderingDirection;
import com.noorq.casser.mapping.annotation.ClusteringColumn;
import com.noorq.casser.mapping.annotation.Constraints;
import com.noorq.casser.mapping.annotation.PartitionKey;
import com.noorq.casser.mapping.annotation.Table;
import com.noorq.casser.mapping.annotation.Types;

/**
 *   MessageQueue is using to schedule Message delivery and track 
 *   queue by senders
 *
 *
 *   It distributed by buckets and each bucket has it's own sender to serve
 */

@Table
public interface MessageQueue {

	static final int DEFAULT_BUCKETS = 1;
	
	/**
	 *  Bucket number is using for distribution purpose, it can be increased as more sender we will have
	 *  
	 *  Finally, we will have Map&lt;Bucket, Sender%gt; distributed to all senders and 
	 *  some balancing algorithm between senders at runtime
	 *  
	 */
	
	@PartitionKey
	int bucket();
	
	/**
	 *  Timestamp when delivery have to be made
	 *  
	 *  We are given to our clients ability to schedule delivery of messages for the future
	 *  
	 *  So, in fact, of the client wants to make some marketing campaign, it can create and submit
	 *  messages before they will go
	 */
	
	@Constraints.NotNull
	@Types.Timeuuid
	@ClusteringColumn(ordering = OrderingDirection.ASC)
	UUID deliveryAt();
	
	/**
	 *  Flag is using to optimistic locking by Sender, can be ignored, depends on Sender algorithm 
	 */
	
	boolean peeked();
	
	/**
	 *  Tracks how many attempts that was made before, initially 0
	 */
	
	int attempt();
	
	/**
	 *  Reference to the Message  for delivery
	 */
	
	@MessageId
	String messageId();

	/**
	 *  Particular recipient of the Message for whom delivery will be made
	 *  
	 *  So, if the have multiple recipients of the message, we need to enqueue multiple
	 *  times the same Message for each recipient
	 *   
	 */
	
	@Constraints.NotNull
	MessageRecipient recipient();
	
}
