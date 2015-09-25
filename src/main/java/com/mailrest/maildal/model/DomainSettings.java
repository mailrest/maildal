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
 *   Domain settings are using to store information about enabled features per domain 
 *
 *   It is useful for the client in case of having different web-sites for different purposes
 *
 */

@UDT
public interface DomainSettings {

	/**
	 *   In case of 'test' environment it will override 'to' field in the message 
	 *   
	 *   @return optional override for 'to' field in test environment
	 */
	
	String testToRecipients();

	/**
	 *   In case of 'test' environment it will override 'bcc' field in the message 
	 *   
	 *   @return optional override for 'bcc' field in test environment
	 */

	String testBccRecipients();

	/**
	 *   In case of 'prod' environment it will override 'bcc' field in the message 
	 *   
	 *   @return optional override for 'bcc' field in prod environment
	 */
	
	String prodBccRecipients();

	/**
	 *   If it is YES, then to all messages in the domain will be added unsubscribe link
	 *   
	 *   @return unsubscribe options
	 */
	
	UnsubscribeOptions unsubscribeLink();
	
	/**
	 *   Optional field that defines template of the text that will be added in case of UnsubscribeOptions.YES
	 *   
	 *   @return unsubscribe template for plain text
	 */
	
	String unsubscribeText();

	/**
	 *   Optional field that defines template of the html that will be added in case of UnsubscribeOptions.YES
	 *   
	 *   @return unsubscribe template for html text
	 */

	String unsubscribeHtml();
	
	/**
	 *  In case of YES all links in the message will be overridden by redirect action
	 *  In case of HTML_ONLY links will be overridden only for HTML mails
	 *  
	 *  @return options for tracking clicks
	 */
	
	TrackingOptions trackClicks();
	
	/**
	 *  In case of YES email will be tracked by special algorithm for opening 
	 *  
	 *  @return options for tracking opens
	 */
	
	TrackingOptions trackOpens();
	
}
