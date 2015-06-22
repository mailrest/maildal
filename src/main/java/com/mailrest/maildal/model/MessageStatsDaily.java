/*
 *      Copyright (C) 2015 Noorq, Inc.
 *      All rights reserved.
 */
package com.mailrest.maildal.model;

import com.mailrest.maildal.model.constraint.AccountId;
import com.mailrest.maildal.model.constraint.DomainId;
import com.noorq.casser.mapping.OrderingDirection;
import com.noorq.casser.mapping.annotation.ClusteringColumn;
import com.noorq.casser.mapping.annotation.Constraints;
import com.noorq.casser.mapping.annotation.PartitionKey;
import com.noorq.casser.mapping.annotation.Table;
import com.noorq.casser.mapping.annotation.Types;

/**
 *  MessageStatsDaily is using to keep information about statistics and display it in the UI
 *  
 *  We need to track how many messages we sent, received, dropped and so on
 *  
 *  Stats can increase in future for more services provided
 *  
 *  Stats will by used in the billing to provide final statement per month per the Account
 *  
 *  All stats are calculated by using Account's timezone
 *
 */

@Table
public interface MessageStatsDaily {

	/**
	 *   Corresponds to a specific Account
	 *   
	 *   @return account id
	 */
	
	@PartitionKey(ordinal=0)
	@AccountId
	String accountId();
	
	/**
	 *   Corresponds to a specific domain
	 *   
	 *   @return domain id
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
 	 *  @return day formatted to integer
 	 *  
	 */	
	
	@Constraints.NotNull
	@ClusteringColumn(ordering=OrderingDirection.DESC)
	int dayAt();
	
	/**
	 * How many incoming messages were received
	 * 
	 * Used in billing
	 * 
	 * @return number of received messages in account
	 */
	
	@Types.Counter
	long received();
	
	/**
	 * How many outgoing messages were delivered
	 * 
	 * Used in billing
	 * 
	 * @return number of delivered messages in account
	 */
	
	@Types.Counter
	long delivered();

	/**
	 * How many outgoing messages were dropped
	 * 
	 * Used in billing
	 * 
	 * @return number of dropped and not delivered messages
	 */

	@Types.Counter
	long dropped();
	
	/*
	
	@Types.Counter
	long opens();

	@Types.Counter
	long clicks();

	@Types.Counter
	long bounces();

	@Types.Counter
	long spamReports();
	
	*/

	/**
	 *  How many recipients unsubscribed 
	 *  
	 *  Used in billing
	 *  
	 *  @return number of unsubscribed users
	 */
	
	@Types.Counter
	long unsubscribed();

}
