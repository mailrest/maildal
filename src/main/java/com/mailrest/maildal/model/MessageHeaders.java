/*
 *      Copyright (C) 2015 Noorq, Inc.
 *      All rights reserved.
 */
package com.mailrest.maildal.model;

import com.noorq.casser.mapping.annotation.Constraints;
import com.noorq.casser.mapping.annotation.UDT;

@UDT
public interface MessageHeaders {

	@Constraints.NotEmpty
	String fromRecipients();
	
	@Constraints.NotEmpty
	String toRecipients();
	
	String ccRecipients();
	
	String bccRecipients();
	
	String subject();
	
}
