/*
 *      Copyright (C) 2015 Noorq, Inc.
 *      All rights reserved.
 */
package com.mailrest.maildal.model;

import com.noorq.casser.mapping.annotation.Constraints;
import com.noorq.casser.mapping.annotation.UDT;

/**
 *  Template is using to pre-format messages before sending them
 *  
 *  Each template has specific engine and fields that can be used to format final message 
 *
 *  User can send userVariables to personalize message
 *
 */

@UDT
public interface Template {

	/**
	 *  Display name of the template in UI 
	 */
	
	String displayName();
	
	/**
	 *  Description of the template and purpose of it 
	 */
	
	String description();
	
	/**
	 *  Template engine that will render the final message 
	 */
	
	@Constraints.NotNull
	TemplateEngine engine();
	
	/**
	 *  Optional field that will be placed to the "from:" field in the Message 
	 */
	
	String fromRecipients();

	/**
	 *  Optional field that will be placed to the "bcc:" field in the Message 
	 */

	String bccRecipients();

	/**
	 *  Optional field that will be placed to the "subject:" field in the Message and template engine
	 *  will be executed with userVariables under it
	 */
	
	String subject();
	
	/**
	 *  Optional field that will be placed to the text body in the Message and template engine
	 *  will be executed with userVariables under it
	 */	
	
	String textBody();

	/**
	 *  Optional field that will be placed to the html body file in the Message and template engine
	 *  will be executed with userVariables under it
	 */		
	
	String htmlBody();
	
}
