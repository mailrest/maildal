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
