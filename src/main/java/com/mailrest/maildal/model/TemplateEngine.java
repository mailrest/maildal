/*
 *      Copyright (C) 2015 Noorq, Inc.
 *      All rights reserved.
 */
package com.mailrest.maildal.model;

/**
 *  TemplateEngine type 
 *
 *  User can have no template engine, so for this case we are using NO type
 *  
 *  Otherwise, the corresponding template engine will be used
 *
 */

public enum TemplateEngine {

	/**
	 * Do not use template engine (in case of non-personalized messages)
	 */
	
	NO,
	
	/**
	 *  Well known legacy template engine
	 */
	
	VELOCITY,

	/**
	 *  New template engine for Scala
	 */

	MUSTACHE;
	
}
