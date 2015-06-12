/*
 *      Copyright (C) 2015 Noorq, Inc.
 *      All rights reserved.
 */
package com.mailrest.maildal.model;


public enum CallbackAction {

	CONFIRM_EMAIL("C"),
	
	RESTORE_PASSWORD("R"); 
	
	private final String code;

	private CallbackAction(String code) {
		this.code = code;
	}

	public String getCode() {
		return this.code;
	}
	
	public static CallbackAction fromCode(String code) {
		for (CallbackAction val : values()) {
			if (val.getCode().equals(code)) {
				return val;
			}
		}
		throw new IllegalArgumentException("code is not found " + code);
	}
	
	
}
