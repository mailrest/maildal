package com.mailrest.maildal.model;

import java.util.Date;
import java.util.List;

import com.noorq.casser.mapping.annotation.ClusteringColumn;
import com.noorq.casser.mapping.annotation.Constraints;
import com.noorq.casser.mapping.annotation.Index;
import com.noorq.casser.mapping.annotation.PartitionKey;
import com.noorq.casser.mapping.annotation.Table;

@Table
public interface AccountDomain {

	@Constraints.Number
	@Constraints.NotEmpty
	@PartitionKey
	String accountId();

	@Constraints.NotEmpty
	@Constraints.LowerCase
	@ClusteringColumn
	String domainId();

	@Constraints.NotNull
	Date createdAt();

	@Constraints.NotEmpty
	String domainIdn();
	
	@Index
	DomainVerificationStatus lastStatus();
	
	List<DomainVerificationEvent> events();
}
