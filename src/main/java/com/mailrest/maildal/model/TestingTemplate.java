/*
 *      Copyright (C) 2015 Noorq, Inc.
 *      All rights reserved.
 */
package com.mailrest.maildal.model;

import com.noorq.casser.mapping.annotation.ClusteringColumn;
import com.noorq.casser.mapping.annotation.Constraints;
import com.noorq.casser.mapping.annotation.PartitionKey;
import com.noorq.casser.mapping.annotation.Table;

@Table
public interface TestingTemplate {

	static final String DEFAULT_ENV = "test";
	
	@Constraints.NotEmpty
	@Constraints.LowerCase
	@PartitionKey(ordinal=0)
	String domainId();

	@Constraints.NotEmpty
	@PartitionKey(ordinal=1)
	String accountId();
	
	@Constraints.NotEmpty
	@Constraints.LowerCase
	@ClusteringColumn(ordinal=0)
	String templateId();

	@Constraints.NotEmpty
	@ClusteringColumn(ordinal=1)
	String environment();

	@Constraints.NotNull
	Template template();
	
}
