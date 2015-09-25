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
