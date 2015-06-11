/*
 *      Copyright (C) 2015 Noorq, Inc.
 *      All rights reserved.
 */
package com.mailrest.maildal.secur;

public interface TokenManager<T> {

	String toJwt(T tokenObj, int durationMinutes);
	
	T fromJwt(String jwt); 
	
}
