/*
 *      Copyright (C) 2015 Noorq, Inc.
 *      All rights reserved.
 */
package com.mailrest.maildal.model;

import com.noorq.casser.mapping.annotation.ClusteringColumn;
import com.noorq.casser.mapping.annotation.PartitionKey;
import com.noorq.casser.mapping.annotation.Table;

@Table
public interface TestingTemplate {

	static final String DEFAULT_ENV = "test";
	
	@PartitionKey(ordinal=0)
	String domain();

	@PartitionKey(ordinal=1)
	String accountId();

	@ClusteringColumn(ordinal=0)
	String name();
	
	/*
	 * For now it will use only the 'test' env
	 */
	
	@ClusteringColumn(ordinal=1)
	String environment();

	Template template();
	
}
