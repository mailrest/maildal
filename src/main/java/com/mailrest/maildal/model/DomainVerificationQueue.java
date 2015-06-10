package com.mailrest.maildal.model;

import java.util.UUID;

import com.noorq.casser.mapping.OrderingDirection;
import com.noorq.casser.mapping.annotation.ClusteringColumn;
import com.noorq.casser.mapping.annotation.Constraints;
import com.noorq.casser.mapping.annotation.PartitionKey;
import com.noorq.casser.mapping.annotation.Table;
import com.noorq.casser.mapping.annotation.Types;

@Table
public interface DomainVerificationQueue {

	static final int DEFAULT_BUCKETS = 11;
	
	@PartitionKey
	int bucket();
	
	@Constraints.NotNull
	@Types.Timeuuid
	@ClusteringColumn(ordering = OrderingDirection.ASC)
	UUID verifyAt();
	
	boolean peeked();
	
	int attempt();
	
	@Constraints.Number
	@Constraints.NotEmpty
	String accountId();

	@Constraints.LowerCase
	@Constraints.NotEmpty
	String domainId();

}
