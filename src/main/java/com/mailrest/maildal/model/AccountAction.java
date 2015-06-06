/*
 *      Copyright (C) 2015 Noorq, Inc.
 *      All rights reserved.
 */
package com.mailrest.maildal.model;

public enum AccountAction {

	/* 
	 * Store ip and userAgent
	 */
	
	LOGIN,
	CHANGE_ACCOUNT_SETTINGS,
	ADD_USER,
	REMOVE_USER,
	CONFIRM_USER_EMAIL,
	
	/*
	 * Store domain
	 */
	
	ADD_DOMAIN,
	REMOVE_DOMAIN,
	CHANGE_DOMAIN_APIKEY,
	CHANGE_DOMAIN_SETTINGS,
	
	/*
	 * Store template
	 */
	
	CREATE_TEMPLATE,
	CHANGE_TEMPLATE,
	DEPLOY_TEMPLATE,
	ROLLBACK_TEMPLATE;
	
}
