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
 *   UserPermission is the enum of hard coded permissions for authorization purpose
 *   
 *   For now, we have three permissions: ADMIN, WRITE and READ_ONLY
 *   
 *  ADMIN - user can add/remove other users, can see all content of the organization, no limits
 *  WRITE - user can change settings, pay bills, add/remove boxes, add/remove templates
 *  READ_ONLY - user can only read account and user information that have to be public in organization   
 *
 */

public enum UserPermission {
	
	ADMIN("A"), 
	WRITE("W"),
	READ_ONLY("R");
	
	/**
	 * Code that is using in JSON of the JWT (Java Web Token)
	 */
	
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
