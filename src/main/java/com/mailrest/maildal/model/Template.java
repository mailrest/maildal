/*
 *      Copyright (C) 2015 Noorq, Inc.
 *      All rights reserved.
 */
package com.mailrest.maildal.model;

import com.noorq.casser.mapping.annotation.Constraints;
import com.noorq.casser.mapping.annotation.UDT;

@UDT
public interface Template {

	String name();
	
	String description();
	
	@Constraints.NotNull
	TemplateEngine engine();
	
	String fromRecipients();

	String bccRecipients();

	String subject();
	
	String textBody();

	String htmlBody();
	
}
