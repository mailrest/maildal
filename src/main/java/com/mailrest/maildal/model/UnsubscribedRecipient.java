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

/**
 *   UnsubscribedRecipient is the table to store all recipients that are clicked to
 *   unsubscribe link
 *   
 *   In case of Message delivery for the specific Account and domain we need to
 *   check recipient in this list to avoid delivery and complaint with sender's policy
 *
 */

@Table
public interface UnsubscribedRecipient {
	
    /**
     *  Corresponds to a specific Account 	
     */

	@PartitionKey(ordinal=0)
	@AccountId
	String accountId();
	
    /**
     *  Corresponds to a specific domain 	
     */
	
	@PartitionKey(ordinal=1)
	@DomainId
	String domainId();

	/**
	 *  Unsubscribed recipient email 
	 */
	
	@ClusteringColumn
	@EmailId
	String email();
	
	/**
	 *  Timestamp of unsubscription 
	 */
	
	@Constraints.NotNull
	Date unsubscribedAt();
	
}
