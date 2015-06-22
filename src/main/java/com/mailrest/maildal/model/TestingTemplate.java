/*
 *      Copyright (C) 2015 Noorq, Inc.
 *      All rights reserved.
 */
package com.mailrest.maildal.model;

import com.mailrest.maildal.model.constraint.AccountId;
import com.mailrest.maildal.model.constraint.DomainId;
import com.mailrest.maildal.model.constraint.TemplateId;
import com.noorq.casser.mapping.annotation.ClusteringColumn;
import com.noorq.casser.mapping.annotation.Constraints;
import com.noorq.casser.mapping.annotation.PartitionKey;
import com.noorq.casser.mapping.annotation.Table;

/**
 *   Special table to store testing templates
 *   
 *   It has different structure rather then table of deployed templates, thereby having
 *   additional field 'environment' that is clustering column in order to retrieve different 
 *   types of the environments
 *   
 *   By default we have 'test' environment for all accounts, but user can create their
 *   own environments like 'qa' or 'stage' for testing purposes
 *   
 *   All templates in TestingTemplate are mutable, but in DeplotyedTemplate are immutable
 */

@Table
public interface TestingTemplate {
	
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
	 *  Environment name, by default is 'test' 
	 *  
	 *  @return environment
	 */
	
	@Constraints.NotEmpty
	@ClusteringColumn(ordinal=1)
	String environment();
	
	/**
	 *  Template itself 
	 *  
	 *  @return template itself
	 */
	
	@Constraints.NotNull
	Template template();
	
}
