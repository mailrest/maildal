/*
 *      Copyright (C) 2015 Noorq, Inc.
 *      All rights reserved.
 */
package com.mailrest.maildal.model;

import java.util.Date;

import com.mailrest.maildal.model.constraint.AccountId;
import com.mailrest.maildal.model.constraint.DomainId;
import com.mailrest.maildal.model.constraint.EmailId;
import com.noorq.casser.mapping.annotation.ClusteringColumn;
import com.noorq.casser.mapping.annotation.Constraints;
import com.noorq.casser.mapping.annotation.PartitionKey;
import com.noorq.casser.mapping.annotation.Table;

@Table
public interface UnsubscribedRecipient {

	@PartitionKey(ordinal=0)
	@DomainId
	String domainId();

	@PartitionKey(ordinal=1)
	@AccountId
	String accountId();

	@ClusteringColumn
	@EmailId
	String email();
	
	@Constraints.NotNull
	Date unsubscribedAt();
	
}
