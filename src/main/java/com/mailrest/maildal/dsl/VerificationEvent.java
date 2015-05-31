package com.mailrest.maildal.dsl;

import java.util.UUID;

import com.noorq.casser.mapping.OrderingDirection;
import com.noorq.casser.mapping.annotation.ClusteringColumn;
import com.noorq.casser.mapping.annotation.PartitionKey;
import com.noorq.casser.mapping.annotation.Table;
import com.noorq.casser.mapping.annotation.Types;

@Table
public interface VerificationEvent {

	@PartitionKey
	String domain();
	
	@Types.Timeuuid
	@ClusteringColumn(ordering=OrderingDirection.DESC)
	UUID eventAt();
	
	VerificationType status();
	
	String message();
	
}
