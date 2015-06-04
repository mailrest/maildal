/*
 *      Copyright (C) 2015 Noorq, Inc.
 *      All rights reserved.
 */
package com.mailrest.maildal.repository;

import com.mailrest.maildal.model.Template;
import com.noorq.casser.core.Casser;


public interface TemplateRepository extends AbstractRepository {
	
	static final Template template = Casser.dsl(Template.class);
	

	
}
