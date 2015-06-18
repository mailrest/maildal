/*
 *      Copyright (C) 2015 Noorq, Inc.
 *      All rights reserved.
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
