/*
 *      Copyright (C) 2015 Noorq, Inc.
 *      All rights reserved.
 */
package com.mailrest.maildal.gen;

public enum Generators {

	ACCOUNT_ID(new AccountId()),
	API_KEY(new ApiKey()),
	COOKIE_ID(new CookieId()),
	LINK_ID(new LinkId()),
	MESSAGE_ID(new MessageId()),
	PASSWORD(new PasswordGen()),
	PASSWORD_SALT(new PasswordSalt()),
	REQUEST_ID(new RequestId());

	private final Object gen;
	
	private Generators(Object gen) {
		this.gen = gen;
	}
	
	public String next() {
		if (gen instanceof SimpleGenerator) {
			return ((SimpleGenerator)gen).next();
		}
		if (gen instanceof ParameterGenerator) {
			return ((ParameterGenerator)gen).next("");
		}
		throw new IllegalStateException("unknown generator type " + gen.getClass());
	}
	
	public String next(String parameter) {
		if (gen instanceof SimpleGenerator) {
			return ((SimpleGenerator)gen).next();
		}
		if (gen instanceof ParameterGenerator) {
			return ((ParameterGenerator)gen).next(parameter);
		}
		throw new IllegalStateException("unknown generator type " + gen.getClass());
	}
	
}
