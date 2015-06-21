package com.mailrest.maildal.model;

import java.util.Map;

import com.mailrest.maildal.model.constraint.AccountId;
import com.mailrest.maildal.model.constraint.BoxId;
import com.mailrest.maildal.model.constraint.DomainId;
import com.noorq.casser.mapping.annotation.ClusteringColumn;
import com.noorq.casser.mapping.annotation.Constraints;
import com.noorq.casser.mapping.annotation.PartitionKey;
import com.noorq.casser.mapping.annotation.Table;

/**
 * Box entity is using to store information about mail box.
 * 
 * We are storing password information and some settings here.
 * 
 */

@Table
public interface Box {

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
	 *  By using clustering column we are able to retrieve all boxes in the domain
	 *  to show in the UI 
	 *  
	 *  BoxId is calculated as lower case of IDN.toASCII from mail box display name
	 *  
	 */	
	
	@ClusteringColumn
	@BoxId
	String boxId();
	
	/**
	 *  Actual display name of the mail box 
	 */
	
	String boxName();
	
	/**
	 * Owner's first name
	 */
	
	String firstName();

	/**
	 * Owner's last name
	 */

	String lastName();
	
	/**
	 *  We are not storing password itself, we are storing only hash of the password 
	 */
	
	@Constraints.NotEmpty
	String passwordHash();
	
	/**
	 *  Security questions are using to help restore password for the owner of the box 
	 */
	
	Map<String, String> securityQuestions();
	
	/**
	 *  Box settings have additional settings for the Box 
	 */
	
	BoxSettings settings();
	
	/**
	 *  Existing folders in the Box 
	 *  
	 *  Key is the folderId
	 */
	
	Map<String, BoxFolder> folders();
	
}
