/*
 *      Copyright (C) 2015 Noorq, Inc.
 *
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
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
