/*
 *      Copyright (C) 2015 Noorq, Inc.
 *      All rights reserved.
 */
package com.mailrest.maildal.repository;

import com.mailrest.maildal.model.Domain;
import com.noorq.casser.core.Casser;

public interface DomainRepository extends AbstractRepository {

	static final Domain domain = Casser.dsl(Domain.class);
	
	
}
