/*
 *      Copyright (C) 2015 Noorq, Inc.
 *
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 */
package com.mailrest.maildal.model;

import java.util.UUID;

import com.noorq.casser.mapping.OrderingDirection;
import com.noorq.casser.mapping.annotation.ClusteringColumn;
import com.noorq.casser.mapping.annotation.Constraints;
import com.noorq.casser.mapping.annotation.PartitionKey;
import com.noorq.casser.mapping.annotation.Table;
import com.noorq.casser.mapping.annotation.Types;

/**
 *  DomainVerificationQueue is using to schedule domains for verification
 *  
 *  Special daemon verifier will peek up domains from this queue, verify them and
 *  then update AccountDomain.events and DomainOwner tables
 *
 */

@Table
public interface DomainVerificationQueue {

	static final int DEFAULT_BUCKETS = 1;
	
	/**
	 *  Random bucket number is using to distribute date in cluster 
	 *  Each verifier will peek up specific buckets for execution
	 *  
	 *  @return bucket number
	 */
	
	@PartitionKey
	int bucket();
	
	/**
	 *  Scheduled time of the verification
	 *  
	 *  @return immutable timestamp of schedule in time queue
	 */
	
	@Constraints.NotNull
	@Types.Timeuuid
	@ClusteringColumn(ordering = OrderingDirection.ASC)
	UUID verifyAt();
	
	/**
	 *  Flag that using to organize optimistic locking for peek up process 
	 *  
	 *  @return true if record was peeked
	 */
	
	boolean peeked();
	
	/**
	 *  Attempt number, track this number to avoid infinity checks 
	 *  
	 *  @return number attempts passed before
	 */
	
	int attempt();
	
	/**
	 *  Account that added domain 
	 *  
	 *  @return account id
	 */
	
	@IdConstraints.AccountId
	String accountId();

	/**
	 *  Domain that have to be verified 
	 *  
	 *  @return domain id
	 */
	
	@IdConstraints.DomainId
	String domainId();

}
