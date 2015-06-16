/*
 *      Copyright (C) 2015 Noorq, Inc.
 *      All rights reserved.
 */
package com.mailrest.maildal.model;

import java.util.Date;

import com.mailrest.maildal.model.constraint.AccountId;
import com.mailrest.maildal.model.constraint.DomainId;
import com.mailrest.maildal.model.constraint.TemplateId;
import com.noorq.casser.mapping.OrderingDirection;
import com.noorq.casser.mapping.annotation.ClusteringColumn;
import com.noorq.casser.mapping.annotation.Constraints;
import com.noorq.casser.mapping.annotation.PartitionKey;
import com.noorq.casser.mapping.annotation.Table;

@Table
public interface DeployedTemplate {
	
	@PartitionKey(ordinal=0)
	@DomainId
	String domainId();

	@PartitionKey(ordinal=1)
	@AccountId
	String accountId();
	
	@ClusteringColumn(ordinal=0)
	@TemplateId
	String templateId();

	@Constraints.NotNull
	@ClusteringColumn(ordinal=1, ordering=OrderingDirection.DESC)
	Date deployedAt();

	@Constraints.NotNull
	Template template();
	
}
