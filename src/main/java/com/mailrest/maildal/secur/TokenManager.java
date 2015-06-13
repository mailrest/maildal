/*
 *      Copyright (C) 2015 Noorq, Inc.
 *      All rights reserved.
 */
package com.mailrest.maildal.secur;

public interface TokenManager<T> {

	String toJwt(T tokenObj, int durationMinutes);
	
	T fromJwt(String jwt); 

	default String toJwtLink(T tokenObj, int durationMinutes) {
		String jwt = toJwt(tokenObj, durationMinutes);
		return TokenLink.INSTANCE.encode(jwt);
	}
	
	default T fromJwtLink(String jwtLink) {
		String jwt = TokenLink.INSTANCE.decode(jwtLink);
		return fromJwt(jwt); 
	}
	
}
