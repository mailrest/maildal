/*
 *      Copyright (C) 2015 Noorq, Inc.
 *      All rights reserved.
 */
package com.mailrest.maildal.repository;


public interface TemplateRepository extends AbstractRepository {
	
	default int createTemplate() {
		return 0;
	}
	
}
