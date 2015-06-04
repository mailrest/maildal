package com.mailrest.maildal.model;

import java.util.Date;
import java.util.List;

import com.noorq.casser.mapping.annotation.ClusteringColumn;
import com.noorq.casser.mapping.annotation.Index;
import com.noorq.casser.mapping.annotation.PartitionKey;
import com.noorq.casser.mapping.annotation.Table;

@Table
public interface AccountDomain {

	@PartitionKey
	String accountId();

	/*
	 * lower-case domain
	 */

	@ClusteringColumn
	String domain();

	Date createdAt();

	@Index
	DomainStatus lastStatus();
	
	List<DomainVerificationEvent> events();
}
