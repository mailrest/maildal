/*
 *      Copyright (C) 2015 Noorq, Inc.
 *      All rights reserved.
 */
package com.mailrest.maildal.model;

import java.util.Date;

import com.noorq.casser.mapping.annotation.Constraints;
import com.noorq.casser.mapping.annotation.UDT;

/**
 *  DomainVerificationEvent is using to store information about verification attempt 
 *
 *  All attempts will be shown in as logs in UI
 *
 */

@UDT
public interface DomainVerificationEvent {

	/**
	 *  Attempt time 
	 *  
	 *  @return immutable timestamp of verification attempt
	 */
	
	@Constraints.NotNull
	Date eventAt();
	
	/**
	 *  Status of the attempt 
	 *  
	 *  @return status of verification
	 */
	
	@Constraints.NotNull
	DomainVerificationStatus status();
	
	/**
	 *  Additional message from the verifier (usually error or response message) 
	 *  
	 *  @return message of the verification daemon
	 */
	
	String message();
	
}
