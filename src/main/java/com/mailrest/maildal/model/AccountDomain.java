package com.mailrest.maildal.model;

import java.util.Date;
import java.util.List;

import com.mailrest.maildal.model.constraint.AccountId;
import com.mailrest.maildal.model.constraint.DomainId;
import com.noorq.casser.mapping.annotation.ClusteringColumn;
import com.noorq.casser.mapping.annotation.Constraints;
import com.noorq.casser.mapping.annotation.PartitionKey;
import com.noorq.casser.mapping.annotation.Table;

/**
 *  Each account is able to add domain name of the business
 *  
 *  After adding this name customer needs to validate domain name,
 *  this particular table is using to store information about added
 *  domain name and the status if the validation
 *
 *  Additionally @User is able to setup settings and apiKey for the domain
 *  after validation this information will be using to authenticate REST API
 *  requests from the clients
 *  
 */

@Table
public interface AccountDomain {

	/**
	 *  Corresponds to a specific Account 
	 */
	
	@PartitionKey
	@AccountId
	String accountId();

	/**
	 *  By using clustering column we are able to retrieve all domain names for the Account and
	 *  show in the UI 
	 *  
	 *  DomainId is calculated as lower case of IDN.toASCII from domain IDN name
	 *  
	 */
	
	@ClusteringColumn
	@DomainId
	String domainId();

	/**
	 *  Time when domain name was added in Account 
	 */
	
	@Constraints.NotNull
	Date createdAt();
	
	/**
	 *  Actual domain name, that will be shown for the user in UI 
	 */
	
	@Constraints.NotEmpty
	String domainIdn();
	
	/**
	 *  Domain verification events that comes from verifier after each attempt
	 *  
	 *  Verifier peeks domain from @DomainVerificationQueue and after verification it
	 *  adds event and in case of successful verification it updates @DomainOwner table
	 */
	
	List<DomainVerificationEvent> events();
	
	/**
	 *   API key is using to authenticate REST requests for the particular domainId
	 * 
	 *   For now we are using randomly generated string in Base62 format
	 * 
	 *   Example: 5602aaa3eeb50233071dae4db2c3eb99
	 */
	
	@Constraints.NotEmpty
	String apiKey();

	/**
	 *   Domain settings are using to store information about some features that are enables for the domain
	 */
	
	@Constraints.NotNull
	DomainSettings settings();
}
