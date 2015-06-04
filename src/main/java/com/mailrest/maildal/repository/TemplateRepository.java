/*
 *      Copyright (C) 2015 Noorq, Inc.
 *      All rights reserved.
 */
package com.mailrest.maildal.repository;

import com.mailrest.maildal.model.TestingTemplate;
import com.noorq.casser.core.Casser;


public interface TemplateRepository extends AbstractRepository {
	
	static final TestingTemplate template = Casser.dsl(TestingTemplate.class);
	

	
}
