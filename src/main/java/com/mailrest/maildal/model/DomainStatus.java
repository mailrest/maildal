/*
 *      Copyright (C) 2015 Noorq, Inc.
 *      All rights reserved.
 */
package com.mailrest.maildal.model;


public enum DomainStatus {

	// New domain added for verification
	ACCEPTED,
	
	// Domain successfully verified
	VERIFIED, 
	
	// Fail to verify domain
	FAILED,
	
	// Do not do periodic check
	SUSPENDED;
	
}
