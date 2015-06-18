/*
 *      Copyright (C) 2015 Noorq, Inc.
 *      All rights reserved.
 */
package com.mailrest.maildal.model;

/**
 *  DomainVerificationStatus very in different situations
 *  
 *  When domain just added it will have ACCEPTED status
 *  When domain successfully verified it will have VERIFIED status
 *  If some error happens in verification process then domain has FAILED status
 *  After N attempts domain marks as SUSPENDED and only User can restart verification in UI
 */

public enum DomainVerificationStatus {

	// New domain added for verification
	ACCEPTED,
	
	// Domain successfully verified
	VERIFIED, 
	
	// Fail to verify domain
	FAILED,
	
	// Do not do periodic check
	SUSPENDED;
	
}
