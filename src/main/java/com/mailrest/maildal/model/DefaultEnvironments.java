/*
 *      Copyright (C) 2015 Noorq, Inc.
 *      All rights reserved.
 */
package com.mailrest.maildal.model;

public enum DefaultEnvironments {

	TEST("test"),
	PROD("prod");
	
	private final String name;
	
	private DefaultEnvironments(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
	
}
