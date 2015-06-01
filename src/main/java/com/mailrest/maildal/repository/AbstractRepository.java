/*
 *      Copyright (C) 2015 Noorq, Inc.
 *      All rights reserved.
 */
package com.mailrest.maildal.repository;

import com.noorq.casser.core.CasserSession;

public interface AbstractRepository {

	CasserSession session();
	
}
