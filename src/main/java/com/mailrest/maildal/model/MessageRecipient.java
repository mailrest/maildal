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

import com.noorq.casser.mapping.annotation.UDT;

/**
 *  MessageRecipient holds information about particular recipient of the Message 
 */

@UDT
public interface MessageRecipient {

	/**
	 *  Full text of the recipient name extracted from toRecipients, ccRecipients and bccRecipients fields in the Message
	 *  
	 *  @return recipient name or null
	 */
	
	String recipientName();
	
	/**
	 *  Email that was extracted from recipient field and normalized by IDN and lower cased
	 *  
	 *  @return parsed and extracted email from recipient, in order to check correctness
	 */
	
	@IdConstraints.EmailId
	String recipientEmail();

}
