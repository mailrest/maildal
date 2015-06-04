package com.mailrest.maildal.model;

import java.util.Date;
import java.util.List;

import com.noorq.casser.mapping.annotation.Index;
import com.noorq.casser.mapping.annotation.PartitionKey;
import com.noorq.casser.mapping.annotation.Table;

@Table
public interface DomainVerificationRequest {

	@PartitionKey
	String requestId();

	Date createdAt();

	/*
	 * lower-case domain
	 */
	
	String domain();
	
	String accountId();
	
	@Index
	DomainStatus lastStatus();
	
	List<DomainVerificationEvent> events();
}
