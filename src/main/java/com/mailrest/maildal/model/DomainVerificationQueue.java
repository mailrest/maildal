package com.mailrest.maildal.model;

import java.util.UUID;

import com.mailrest.maildal.model.constraint.AccountId;
import com.mailrest.maildal.model.constraint.DomainId;
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
	
	@AccountId
	String accountId();

	/**
	 *  Domain that have to be verified 
	 *  
	 *  @return domain id
	 */
	
	@DomainId
	String domainId();

}
