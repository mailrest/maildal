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

import com.noorq.casser.mapping.annotation.Constraints;
import com.noorq.casser.mapping.annotation.UDT;

/**
 *   MessageSender information that is using to track what server and 
 *   what IP of the server made delivery attempt
 *
 *   For the next time we need to select another bucket and schedule
 *   Message delivery for different sender
 *
 *   In logs we will see that Message was tried to delivery by different senders
 *   before it's dropped
 *
 */


@UDT
public interface MessageSender {

	/**
	 *   Sender's name, usually pid@hostname 
	 *   
	 *   @return sender name
	 */
	
	@Constraints.NotEmpty
	String senderName();
	
	/**
	 *   Sender's public IP
	 *   
	 *   @return sender's public IP address
	 */
	
	@Constraints.NotEmpty
	String senderIp();

}
