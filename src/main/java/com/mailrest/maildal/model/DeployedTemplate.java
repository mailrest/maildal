/*
 *      Copyright (C) 2015 Noorq, Inc.
 *      All rights reserved.
 */
package com.mailrest.maildal.model;

import java.util.Date;

import com.noorq.casser.mapping.OrderingDirection;
import com.noorq.casser.mapping.annotation.ClusteringColumn;
import com.noorq.casser.mapping.annotation.Constraints;
import com.noorq.casser.mapping.annotation.PartitionKey;
import com.noorq.casser.mapping.annotation.Table;

@Table
public interface DeployedTemplate {

	static final String DEFAULT_ENV = "prod";
	
	/*
	 * lower-case domain name
	 */
	
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

	@Constraints.NotNull
	@ClusteringColumn(ordinal=1, ordering=OrderingDirection.DESC)
	Date deployedAt();

	@Constraints.NotNull
	Template template();
	
}
