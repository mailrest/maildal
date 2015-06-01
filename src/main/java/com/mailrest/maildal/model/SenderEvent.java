/*
 *      Copyright (C) 2015 Noorq, Inc.
 *      All rights reserved.
 */
package com.mailrest.maildal.model;

import java.util.UUID;

import com.noorq.casser.mapping.OrderingDirection;
import com.noorq.casser.mapping.annotation.ClusteringColumn;
import com.noorq.casser.mapping.annotation.PartitionKey;
import com.noorq.casser.mapping.annotation.Table;
import com.noorq.casser.mapping.annotation.Types;

@Table
public interface SenderEvent {

	@PartitionKey
	String accountId();
	
	@Types.Timeuuid
	@ClusteringColumn(ordering=OrderingDirection.DESC)
	UUID eventAt();
	
	String messageId();
	
	ActionType actionType();
	
}
