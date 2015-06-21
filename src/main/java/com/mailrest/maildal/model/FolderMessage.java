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
import com.noorq.casser.mapping.annotation.Types;

/**
 *  FolderMessage is using to store Message references inside the folders and orginize access to them by date 
 *
 */

public interface FolderMessage {

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
	 *  Corresponds to a specific box 
	 */
	
	@PartitionKey(ordinal=2)
	@BoxId
	String boxId();

	/**
	 *  Corresponds to a specific folder 
	 */		
	
	@PartitionKey(ordinal=2)
	@FolderId
	String folderId();
	
	/**
	 * 	By using clustering column we are able to retrieve messages ordered by date
	 *  to show in the UI 
	 */
	
	@ClusteringColumn(ordering=OrderingDirection.DESC)
	@Constraints.NotNull
	@Types.Timeuuid
	UUID addedAt();
	
	/**
	 *  Corresponds to a specific message
	 */
	
	@MessageId
	String messageId();
	
	/**
	 * Flag to select unread messages
	 * 
	 * @return true is message readed
	 */
	
	boolean readed();
	
}
