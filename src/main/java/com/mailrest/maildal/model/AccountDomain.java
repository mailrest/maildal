package com.mailrest.maildal.model;

import java.util.Date;
import java.util.List;

import com.mailrest.maildal.model.constraint.AccountId;
import com.mailrest.maildal.model.constraint.DomainId;
import com.noorq.casser.mapping.annotation.ClusteringColumn;
import com.noorq.casser.mapping.annotation.Constraints;
import com.noorq.casser.mapping.annotation.Index;
import com.noorq.casser.mapping.annotation.PartitionKey;
import com.noorq.casser.mapping.annotation.Table;

@Table
public interface AccountDomain {

	@PartitionKey
	@AccountId
	String accountId();

	@ClusteringColumn
	@DomainId
	String domainId();

	@Constraints.NotNull
	Date createdAt();

	@Constraints.NotEmpty
	String domainIdn();
	
	@Index
	DomainVerificationStatus lastStatus();
	
	List<DomainVerificationEvent> events();
}
