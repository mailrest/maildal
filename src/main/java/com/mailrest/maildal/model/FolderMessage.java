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

import com.mailrest.maildal.model.constraint.AccountId;
import com.mailrest.maildal.model.constraint.BoxId;
import com.mailrest.maildal.model.constraint.DomainId;
import com.mailrest.maildal.model.constraint.FolderId;
import com.mailrest.maildal.model.constraint.MessageId;
import com.noorq.casser.mapping.OrderingDirection;
import com.noorq.casser.mapping.annotation.ClusteringColumn;
import com.noorq.casser.mapping.annotation.Constraints;
import com.noorq.casser.mapping.annotation.PartitionKey;
import com.noorq.casser.mapping.annotation.Table;
import com.noorq.casser.mapping.annotation.Types;

/**
 *  FolderMessage is using to store Message references inside the folders and organize access to them by date 
 *
 */

@Table
public interface FolderMessage {

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
	 *  Corresponds to a specific box 
	 *  
	 *  @return box id
	 */
	
	@PartitionKey(ordinal=2)
	@BoxId
	String boxId();

	/**
	 *  Corresponds to a specific folder 
	 *  
	 *  @return folder id
	 */		
	
	@PartitionKey(ordinal=3)
	@FolderId
	String folderId();
	
	/**
	 * 	By using clustering column we are able to retrieve messages ordered by date
	 *  to show in the UI 
	 *  
	 *  @return immutable time when message was added to the folder
	 */
	
	@ClusteringColumn(ordering=OrderingDirection.DESC)
	@Constraints.NotNull
	@Types.Timeuuid
	UUID addedAt();
	
	/**
	 *  Corresponds to a specific message
	 *  
	 *  @return message id
	 */
	
	@MessageId
	String messageId();
	
	/**
	 * Flag to select unread messages
	 * 
	 * @return true if message was readed
	 */
	
	boolean readed();
	
}
