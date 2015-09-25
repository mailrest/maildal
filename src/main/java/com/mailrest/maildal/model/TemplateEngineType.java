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
 *  TemplateEngineType 
 *
 *  User can have no template engine, so for this case we are using NO type
 *  
 *  Otherwise, the corresponding template engine will be used
 *
 */

public enum TemplateEngineType {

	/**
	 * Do not use template engine (in case of non-personalized messages)
	 */
	
	NO(""),
	
	/**
	 *  Mustache template language for Java/Scala
	 */

	MUSTACHE(".mustashe"),
	
	/**
	*   The Jade style of Haml/Scaml template lanaguage
	*/
	
	JADE(".jade"),
	
	/**
	*   Provides Haml style template lanaguage
	*/
	
	SCAML(".scaml"),
	
	/**
	*  Provides a JSP, Erb and Velocity style template language
	*/
	
	SSP(".ssp");

	private String extension;
	
	private TemplateEngineType(String extension) {
		this.extension = extension;
	}

	public String getExtension() {
		return extension;
	}

}
