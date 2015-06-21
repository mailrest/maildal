/*
 *      Copyright (C) 2015 Noorq, Inc.
 *      All rights reserved.
 */
package com.mailrest.maildal.model;

import java.util.Date;

import com.mailrest.maildal.model.constraint.AccountId;
import com.mailrest.maildal.model.constraint.DomainId;
import com.noorq.casser.mapping.OrderingDirection;
import com.noorq.casser.mapping.annotation.ClusteringColumn;
import com.noorq.casser.mapping.annotation.Constraints;
import com.noorq.casser.mapping.annotation.PartitionKey;
import com.noorq.casser.mapping.annotation.Table;

/**
 *  In case of successful verification domain name will be linked to some accountId
 *  
 *  This table is using to link domainId to accountId
 *  
 *  If we remove domain from the Account, then we are deleting all the links in this table as well
 *
 *  This table is using every time of the domain specific REST operation in API to detect AccountId
 *  after that all operations are having AccountId and all permissions are validated around this fact
 *
 */

@Table
public interface DomainOwner {

	/**
	 *  Specific domainId that successfully verified 
	 *  
	 *  @return domain id
	 */
	
	@PartitionKey
	@DomainId
	String domainId();
	
	/**
	 *  Time of the successful verification
	 *  
	 *  @return immutable timestamp when domain was verified
	 */
	
	@Constraints.NotNull
	@ClusteringColumn(ordering=OrderingDirection.DESC)
	Date verifiedAt();

	/**
	 *  Corresponding Account that added this domain name
	 *  
	 *  @return account id
	 */
	
	@AccountId
	String accountId();
	
}
