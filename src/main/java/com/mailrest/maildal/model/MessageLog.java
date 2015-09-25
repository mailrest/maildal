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
import com.mailrest.maildal.model.constraint.DomainId;
import com.mailrest.maildal.model.constraint.MessageId;
import com.noorq.casser.mapping.OrderingDirection;
import com.noorq.casser.mapping.annotation.ClusteringColumn;
import com.noorq.casser.mapping.annotation.Constraints;
import com.noorq.casser.mapping.annotation.PartitionKey;
import com.noorq.casser.mapping.annotation.Table;
import com.noorq.casser.mapping.annotation.Types;


/**
 *    MessageLog table is using to store delivery logs day by day
 *    
 *    This particular table is the view, that is the source of logs in UI
 *
 *    We need to display logs by domain and daily, we are using Account's timezone information to 
 *    find the right day of the event
 */

@Table
public interface MessageLog {

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
 	 *  By using Calendar and Timezone information from Account we are converting
 	 *  eventAt timestamp in UTC date format to 'yyyy-mm-dd' simple date format, then
 	 *  we are calculating the dayAt by using this formula: yyyy * 10000 + mm * 100 + dd and store 
 	 *  this as a integer
 	 *  
 	 *  For example: 20150101
 	 *  
 	 *  @return day in the integer format
 	 *  
	 */
	
	@Constraints.NotNull
	@PartitionKey(ordinal=2)
	int dayAt();

	/**
	 *  Timestamp in TimeUUID format to store particular event
	 *  
	 *  We need UUID, because some messages can be delivered in the same millisecond,
	 *  of course it is not so often but can happen
	 *  
	 *  @return timestamp of the event in TimeUUID
	 */
	
	@Constraints.NotNull
	@Types.Timeuuid
	@ClusteringColumn(ordering = OrderingDirection.DESC)
	UUID eventAt();
	
	/**
	 *  Reference to the Message  
	 *  
	 *  @return message id
	 */
	
	@MessageId
	String messageId();

	/**
	 *  Reference to the our client's collision id  
	 *  
	 *  @return collision id
	 */

	String collisionId();
	
	/**
	 *  Result of the action
	 *  
	 *  @return message delivery action type
	 */
	
	@Constraints.NotNull
	MessageAction action();

	/**
	 *  Delivery information if has 
	 *  
	 *  @return message delivery if exists
	 */
	
	MessageDelivery delivery();

	/**
	 *  Sender information if has 
	 *  
	 *  @return sender information if exists
	 */
	
	MessageSender sender();
	
	/**
	 *  Recipient for whom delivery was made 
	 *  
	 *  @return recipient information
	 */
	
	MessageRecipient recipient();
	
	/**
	 *  Message headers to show in UI 
	 *  
	 *  @return headers information
	 */
	
	MessageHeaders headers();
	
}
