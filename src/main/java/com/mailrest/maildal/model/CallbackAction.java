/*
 *      Copyright (C) 2015 Noorq, Inc.
 *      All rights reserved.
 */
package com.mailrest.maildal.model;

/**
 *   CallbackAction is the type of the action that user is doing by receiving email from MailRest 
 *
 *   This action can be confirming email or restoring password
 *   
 *   We are not storing passwords in plain text, so restoring password always means it's update
 *
 */

public enum CallbackAction {

	CONFIRM_EMAIL("M"),
	
	UPDATE_PASSWORD("P"); 
	
	/**
	 *  One character code is using in JWT (Java Web Token)
	 */
	
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
