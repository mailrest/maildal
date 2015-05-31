package com.mailrest.maildal.dsl;

import java.util.Date;

import com.noorq.casser.mapping.OrderingDirection;
import com.noorq.casser.mapping.annotation.ClusteringColumn;
import com.noorq.casser.mapping.annotation.PartitionKey;
import com.noorq.casser.mapping.annotation.Table;

@Table
public interface LoginEvent {

	@PartitionKey
	String accountId();

	@ClusteringColumn(ordering=OrderingDirection.DESC)
	Date loginAt();
	
	String ip();

	String userAgent();

}
