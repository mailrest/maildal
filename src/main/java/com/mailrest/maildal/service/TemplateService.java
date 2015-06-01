/*
 *      Copyright (C) 2015 Noorq, Inc.
 *      All rights reserved.
 */
package com.mailrest.maildal.service;


public interface TemplateService extends AbstractService {
	
	default int createTemplate() {
		return 0;
	}
	
}
