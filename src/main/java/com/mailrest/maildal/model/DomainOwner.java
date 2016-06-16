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
	@IdConstraints.DomainId
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
	
	@IdConstraints.AccountId
	String accountId();
	
}
