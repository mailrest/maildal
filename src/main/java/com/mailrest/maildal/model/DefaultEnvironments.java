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
