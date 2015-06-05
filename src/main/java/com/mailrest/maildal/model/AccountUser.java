/*
 *      Copyright (C) 2015 Noorq, Inc.
 *      All rights reserved.
 */
package com.mailrest.maildal.model;

import com.noorq.casser.mapping.annotation.UDT;

@UDT
public interface AccountUser {
	
	/*
	 * As it was entered by user, not a lower-case
	 */
	
	String email();
	
	String firstName();

	String lastName();
	
}
