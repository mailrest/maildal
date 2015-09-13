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
	 *  @return bucket number
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
	 *  
	 *  @return scheduled delivery timestamp in TimeUUID
	 */
	
	@Constraints.NotNull
	@Types.Timeuuid
	@ClusteringColumn(ordering = OrderingDirection.ASC)
	UUID deliveryAt();
	
	/**
	 *  Flag is using for optimistic locking by Sender, can be ignored, depends on Sender algorithm 
	 *  
	 *  @return true is object was peeked
	 */
	
	boolean peeked();
	
	/**
	 *  Tracks how many attempts that was made before, initially 0
	 *  
	 *  @return number of passed attempts
	 */
	
	int attempt();
	
	/**
	 *  Reference to the Message  for delivery
	 *  
	 *  @return message id
	 */
	
	@MessageId
	String messageId();

	/**
	 *  Particular recipient of the Message for whom delivery will be made
	 *  
	 *  So, if the have multiple recipients of the message, we need to enqueue multiple
	 *  times the same Message for each recipient
	 *  
	 *  @return recipient of the delivery
	 *   
	 */
	
	@Constraints.NotNull
	MessageRecipient recipient();
	
}
