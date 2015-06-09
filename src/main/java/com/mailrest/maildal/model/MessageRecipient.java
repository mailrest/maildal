/*
 *      Copyright (C) 2015 Noorq, Inc.
 *      All rights reserved.
 */
package com.mailrest.maildal.model;

import com.noorq.casser.mapping.annotation.Constraints;
import com.noorq.casser.mapping.annotation.UDT;

@UDT
public interface MessageRecipient {

	@Constraints.NotEmpty
	String recipient();
	
	@Constraints.NotEmpty
	@Constraints.Email
	@Constraints.LowerCase
	String recipientEmail();

}
