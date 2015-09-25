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
 *  For the log purposes we need to known what particular operation means
 *  
 *  This is the type of the operation that made by some user in the account
 *  
 *  Needs to track changes and helps for customer to understand how account maintains
 *  
 *  Later some billing operations will be added
 *
 */

public enum AccountAction {

	/** 
	 * Store ip and userAgent
	 */
	
	LOGIN,
	CHANGE_ACCOUNT_SETTINGS,
	ADD_USER,
	REMOVE_USER,
	CONFIRM_USER_EMAIL,
	
	/**
	 * Store domainId
	 */
	
	ADD_DOMAIN,
	REMOVE_DOMAIN,
	CHANGE_DOMAIN_APIKEY,
	CHANGE_DOMAIN_SETTINGS,
	
	/**
	 * Store templateId
	 */
	
	CREATE_TEMPLATE,
	CHANGE_TEMPLATE,
	DEPLOY_TEMPLATE,
	ROLLBACK_TEMPLATE;
	
}
