package com.mailrest.maildal.model;

import java.util.UUID;

import com.noorq.casser.mapping.OrderingDirection;
import com.noorq.casser.mapping.annotation.ClusteringColumn;
import com.noorq.casser.mapping.annotation.PartitionKey;
import com.noorq.casser.mapping.annotation.Table;
import com.noorq.casser.mapping.annotation.Types;

@Table
public interface MessageQueue {

	static final int DEFAULT_BUCKETS = 255;
	
	@PartitionKey
	int bucket();
	
	@Types.Timeuuid
	@ClusteringColumn(ordering = OrderingDirection.ASC)
	UUID deliveryAt();
	
	boolean peeked();
	
	int attempt();
	
	String messageId();
	
}
