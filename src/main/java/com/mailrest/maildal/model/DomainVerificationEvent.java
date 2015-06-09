/*
 *      Copyright (C) 2015 Noorq, Inc.
 *      All rights reserved.
 */
package com.mailrest.maildal.model;

import java.util.Date;

import com.noorq.casser.mapping.annotation.Constraints;
import com.noorq.casser.mapping.annotation.UDT;

@UDT
public interface DomainVerificationEvent {

	@Constraints.NotNull
	Date eventAt();
	
	@Constraints.NotNull
	DomainVerificationStatus status();
	
	String message();
	
}
