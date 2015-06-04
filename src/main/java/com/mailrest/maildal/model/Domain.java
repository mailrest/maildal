/*
 *      Copyright (C) 2015 Noorq, Inc.
 *      All rights reserved.
 */
package com.mailrest.maildal.model;

import java.util.Date;
import java.util.Set;

import com.noorq.casser.mapping.annotation.PartitionKey;
import com.noorq.casser.mapping.annotation.Table;

@Table
public interface Domain {

	/*
	 * Lower-case domain name, for example lt.su
	 */
	
	@PartitionKey
	String domain();
	
	/*
	 * Linked to accountId
	 */
	
	String accountId();
	
	Date createdAt();
	
	boolean verified();
	
	/*
	 * Example: key-5602aaa3eeb50233071dae4db2c3eb99
	 */
	
	String apiKey();
	
	Set<String> templates();
	
}
