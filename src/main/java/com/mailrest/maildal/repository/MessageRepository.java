/*
 *      Copyright (C) 2015 Noorq, Inc.
 *      All rights reserved.
 */
package com.mailrest.maildal.repository;

import com.mailrest.maildal.model.Message;
import com.noorq.casser.core.Casser;

public interface MessageRepository extends AbstractRepository {

	static final Message message = Casser.dsl(Message.class);
	
}
