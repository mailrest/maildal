package com.mailrest.maildal.model;

import java.util.Date;

import com.noorq.casser.mapping.OrderingDirection;
import com.noorq.casser.mapping.annotation.ClusteringColumn;
import com.noorq.casser.mapping.annotation.PartitionKey;
import com.noorq.casser.mapping.annotation.Table;

@Table
public interface DeployedTemplate {

	@PartitionKey(ordinal=0)
	String domain();

	@PartitionKey(ordinal=1)
	String accountId();

	@ClusteringColumn(ordinal=0)
	String name();

	@ClusteringColumn(ordinal=1, ordering=OrderingDirection.DESC)
	Date deployedAt();

	Template template();
	
}
