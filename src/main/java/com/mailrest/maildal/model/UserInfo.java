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

import com.mailrest.maildal.model.constraint.UserId;
import com.noorq.casser.mapping.annotation.Constraints;
import com.noorq.casser.mapping.annotation.UDT;

/**
 *  UserInfo is using to store user related information in AccountLog
 *  
 *  Usually all actions are user initiated actions, so this structure have to be always filled
 *
 */

@UDT
public interface UserInfo {
	
	/**
	 *  Corresponds to a particular User 
	 *  
	 *  @return user id
	 */
	
	@UserId
	String userId();
	
	/**
	 *  IP address of the request 
	 *  
	 *  @return IP address
	 */
	
	@Constraints.NotEmpty
	String ip();

	/**
	 *  UserAgent of the request if has
	 *  
	 *  @return UserAgent header
	 */

	String userAgent();
	
}