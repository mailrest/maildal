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

import java.util.Date;

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
     *  
     *  @return account id
     */

	@PartitionKey(ordinal=0)
	@IdConstraints.AccountId
	String accountId();
	
    /**
     *  Corresponds to a specific domain
     *  
     *   @return domain id	
     */
	
	@PartitionKey(ordinal=1)
	@IdConstraints.DomainId
	String domainId();

	/**
	 *  Unsubscribed recipient email 
	 *  
	 *  @return normalized email
	 */
	
	@ClusteringColumn
	@IdConstraints.EmailId
	String email();
	
	/**
	 *  Timestamp of unsubscription 
	 *  
	 *  @return timestamp of unsubscribe event
	 */
	
	@Constraints.NotNull
	Date unsubscribedAt();
	
}
