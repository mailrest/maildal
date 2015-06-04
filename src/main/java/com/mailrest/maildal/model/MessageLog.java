package com.mailrest.maildal.model;

import java.util.Date;

import com.noorq.casser.mapping.OrderingDirection;
import com.noorq.casser.mapping.annotation.ClusteringColumn;
import com.noorq.casser.mapping.annotation.PartitionKey;
import com.noorq.casser.mapping.annotation.StaticColumn;
import com.noorq.casser.mapping.annotation.Table;
import com.noorq.casser.mapping.annotation.Types;

/**
 *   Use TTL 60 Days
 */

@Table
public interface MessageLog {

	@PartitionKey(ordinal=0)
	String accountId();
	
	@PartitionKey(ordinal=1)
	String domain();
	
	@PartitionKey(ordinal=2)
	Date daily();
	
	@StaticColumn
	@Types.Counter
	long counter();
	
	@ClusteringColumn(ordering = OrderingDirection.DESC)
	Date eventAt();
	
	String messageId();
	
	ActionType actionType();

	int spentMilliseconds();
	
	String answer();
	
	String senderId();
	
}
