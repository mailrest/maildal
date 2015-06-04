package com.mailrest.maildal.model;

import java.util.Date;
import java.util.List;

import com.noorq.casser.mapping.OrderingDirection;
import com.noorq.casser.mapping.annotation.ClusteringColumn;
import com.noorq.casser.mapping.annotation.PartitionKey;
import com.noorq.casser.mapping.annotation.Table;

@Table
public interface MessageQueue {

	static final int DEFAULT_BUCKETS = 255;
	
	@PartitionKey
	int bucket();

	@ClusteringColumn(ordering = OrderingDirection.DESC)
	Date deliveryAt();
	
	List<String> messages();
	
}
