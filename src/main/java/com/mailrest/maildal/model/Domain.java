/*
 *      Copyright (C) 2015 Noorq, Inc.
 *      All rights reserved.
 */
package com.mailrest.maildal.model;

import java.util.Date;

import com.noorq.casser.mapping.annotation.ClusteringColumn;
import com.noorq.casser.mapping.annotation.Constraints;
import com.noorq.casser.mapping.annotation.PartitionKey;
import com.noorq.casser.mapping.annotation.StaticColumn;
import com.noorq.casser.mapping.annotation.Table;

@Table
public interface Domain {

	@Constraints.NotEmpty
	@Constraints.LowerCase
	@PartitionKey
	String domainId();
	
	@Constraints.Number
	@Constraints.NotEmpty
	@ClusteringColumn
	String accountId();

	@StaticColumn
	@Constraints.NotEmpty
	String domainIdn();

	@Constraints.NotNull
	Date createdAt();
	
	/*
	 * Example: key-5602aaa3eeb50233071dae4db2c3eb99
	 */
	
	@Constraints.NotEmpty
	String apiKey();

	@Constraints.NotNull
	DomainSettings settings();
	
}
