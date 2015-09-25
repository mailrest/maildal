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

import com.mailrest.maildal.model.constraint.AccountId;
import com.mailrest.maildal.model.constraint.DomainId;
import com.mailrest.maildal.model.constraint.TemplateId;
import com.noorq.casser.mapping.OrderingDirection;
import com.noorq.casser.mapping.annotation.ClusteringColumn;
import com.noorq.casser.mapping.annotation.Constraints;
import com.noorq.casser.mapping.annotation.PartitionKey;
import com.noorq.casser.mapping.annotation.Table;

/**
 *   Special table to store deployed templates
 *   
 *   It has different structure rather then table of testing templates, thereby having
 *   additional field 'deployedAt' that is clustering column in order to retrieve the sequence
 *   of the deployed templates
 *
 *   All templates in DeployedTemplate are immutable, but in TestingTemplate are mutable
 */

@Table
public interface DeployedTemplate {
	
	/**
	 *  Corresponds to a specific Account 
	 *  
	 *  @return account id
	 */
	
	@PartitionKey(ordinal=0)
	@AccountId
	String accountId();
	
	/**
	 *  Corresponds to a specific domain
	 *  
	 *  This is done by the reason, that Account can manage different types
	 *  of web-sites for the client, so in this case client can group his
	 *  activities by domain names and use single Account for billing
	 *  
	 *  @return domain id
	 *  
	 */
	
	@PartitionKey(ordinal=1)
	@DomainId
	String domainId();
	
	/**
	 *  Template id is a unique name of the template, 
	 *  usually it is the lower case display name of the template 
	 *  
	 *  @return template id
	 */
	
	@ClusteringColumn(ordinal=0)
	@TemplateId
	String templateId();

	/**
	 *  Time when template was deployed 
	 *  
	 *  @return immutable time when template was deployed
	 */
	
	@Constraints.NotNull
	@ClusteringColumn(ordinal=1, ordering=OrderingDirection.DESC)
	Date deployedAt();

	/**
	 *  Template itself 
	 *  
	 *  @return template UDT
	 */
	
	@Constraints.NotNull
	Template template();
	
}
