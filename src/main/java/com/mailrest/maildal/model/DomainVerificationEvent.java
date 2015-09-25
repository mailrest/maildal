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
