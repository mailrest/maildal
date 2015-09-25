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
	 *  
	 *  @return display name of the template
	 */
	
	String displayName();
	
	/**
	 *  Description of the template and purpose of it 
	 *  
	 *  @return description of the template
	 */
	
	String description();
	
	/**
	 *  Template engine that will render the final message 
	 *  
	 *  @return engine type of the template
	 */
	
	@Constraints.NotNull
	TemplateEngine engine();
	
	/**
	 *  Optional field that will be placed to the "from:" field in the Message 
	 *  
	 *  @return optional field 'from' in email
	 */
	
	String fromRecipients();

	/**
	 *  Optional field that will be placed to the "bcc:" field in the Message
	 *  
	 *  @return optional field 'bcc' in email
	 */

	String bccRecipients();

	/**
	 *  Optional field that will be placed to the "subject:" field in the Message and template engine
	 *  will be executed with userVariables under it
	 *  
	 *  @return optional field 'subject' in email
	 */
	
	String subject();
	
	/**
	 *  Optional field that will be placed to the text body in the Message and template engine
	 *  will be executed with userVariables under it
	 *  
	 *  @return optional field 'body' in text format 
	 */	
	
	String textBody();

	/**
	 *  Optional field that will be placed to the HTML body file in the Message and template engine
	 *  will be executed with userVariables under it
	 *  
	 *  @return optional field 'body' in HTML format 
	 */		
	
	String htmlBody();
	
}
