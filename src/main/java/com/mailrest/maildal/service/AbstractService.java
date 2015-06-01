/*
 *      Copyright (C) 2015 Noorq, Inc.
 *      All rights reserved.
 */
package com.mailrest.maildal.service;

import com.noorq.casser.core.CasserSession;

public interface AbstractService {

	CasserSession session();
	
}
