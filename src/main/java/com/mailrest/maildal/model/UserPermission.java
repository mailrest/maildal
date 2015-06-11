/*
 *      Copyright (C) 2015 Noorq, Inc.
 *      All rights reserved.
 */
package com.mailrest.maildal.model;

public enum UserPermission {
	
	ADMIN("A"), 
	WRITE("W"),
	READ_ONLY("R");
	
	private final String code;

	private UserPermission(String code) {
		this.code = code;
	}

	public String getCode() {
		return this.code;
	}
	
	public static UserPermission fromCode(String code) {
		for (UserPermission val : values()) {
			if (val.getCode().equals(code)) {
				return val;
			}
		}
		throw new IllegalArgumentException("code is not found " + code);
	}
	
}
