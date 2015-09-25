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
	 *  
	 *  @return account id
	 */
	
	@PartitionKey(ordinal=0)
	@AccountId
	String accountId();

	/**
	 *  Corresponds to a specific domain 
	 *  
	 *  @return domain id
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
	 *  @return box id
	 *  
	 */	
	
	@ClusteringColumn
	@BoxId
	String boxId();
	
	/**
	 *  Actual display name of the mail box 
	 *  
	 *  @return box display name
	 */
	
	@Constraints.NotEmpty
	String boxName();
	
	/**
	 * Owner's first name
	 * 
	 * @return first name of owner if exists
	 */
	
	String firstName();

	/**
	 * Owner's last name
	 * 
	 * @return last name of the owner if exists
	 */

	String lastName();
	
	/**
	 *  We are not storing password itself, we are storing only hash of the password 
	 *  
	 *  @return password hash
	 */
	
	@Constraints.NotEmpty
	String passwordHash();
	
	/**
	 *  Security questions are using to help restore password for the owner of the box 
	 *  
	 *  @return security questions map if exists
	 */
	
	Map<String, String> securityQuestions();
	
	/**
	 *  Box settings have additional settings for the Box 
	 *  
	 *  @return box settings
	 */
	
	BoxSettings settings();
	
	/**
	 *  Existing folders in the Box 
	 *  
	 *  Key is the folderId
	 *  
	 *  @return all folders in the box
	 */
	
	Map<String, BoxFolder> folders();
	
}
