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
 *  MessageDelivery is the information about delivery attempt, who made, when, what happened 
 *
 *  Every time when Sender tries to deliver Message it establish connection with recipient's server
 *  and by using special SMTP protocol tries to deliver it
 *  
 *  Result of the delivery stores in this UDT
 *
 */

@UDT
public interface MessageDelivery {

	/**
	 * Host was used to message delivery, usually retrieved from email address in IDN form
	 * 
	 * @return host from email address
	 */
	
	String deliveryHost();
	
	/**
	 * Does server use the TLS(former SSL) encryption protocol
	 * 
	 * @return true if TLS protocol was used by mail server
	 */
	
	boolean tls();
	
	/**
	 * Did server certificate verify by the sender? 
	 * 
	 * @return true if mail server was verified
	 */
	
	boolean certificateVerified();
	
	/**
	 * Recipient's host name that was selected from MX records of the domain 
	 * 
	 * @return MX host of the mail server
	 */
	
	String mxHost();
	
	/**
	 *  Duration is seconds of the session
	 *  
	 *  @return session duration in seconds
	 */
	
	double sessionSeconds();
	
	/**
	 *  Final code returned by recipient's server 
	 *  
	 *  @return code of the response
	 */
	
	int code();
	
	/**
	 *  Description of the code returned by recipient's server 
	 *  
	 *  @return description of the response
	 */
	
	String description();
	
	/**
	 *  Additional message about delivery, can be error/exception name 
	 *  
	 *  @return message of the response
	 */
	
	String message();
	
}
