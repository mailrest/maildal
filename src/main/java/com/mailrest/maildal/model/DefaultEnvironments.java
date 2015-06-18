/*
 *      Copyright (C) 2015 Noorq, Inc.
 *      All rights reserved.
 */
package com.mailrest.maildal.model;

/**
 *   Hard coded environments that are using for templates
 *   
 *   By default template has 'test' environment that can be changed by user and
 *   'prod' environment where user can deploy/rollback immutable templates
 *
 */

public enum DefaultEnvironments {

	TEST("test"),
	PROD("prod");
	
	/**
	 * Name that is using in REST API
	 */
	
	private final String name;
	
	private DefaultEnvironments(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
	
}
