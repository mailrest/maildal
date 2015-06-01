/*
 *      Copyright (C) 2015 Noorq, Inc.
 *      All rights reserved.
 */
package com.mailrest.maildal.dsl;

import com.noorq.casser.mapping.annotation.PartitionKey;
import com.noorq.casser.mapping.annotation.Table;

@Table
public interface Template {

	@PartitionKey(ordinal=0)
	String domain();

	@PartitionKey(ordinal=1)
	String name();
	
	TemplateEngine engine();
	
	String from();
	
	String subject();
	
	String textBody();

	String htmlBody();

}
