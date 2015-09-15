/*
 *      Copyright (C) 2015 Noorq, Inc.
 *      All rights reserved.
 */
package com.mailrest.maildal.support;

public final class InvalidEmailException extends IllegalArgumentException {

	private static final long serialVersionUID = 298837478573208834L;
	
	private final String email;
	
	public InvalidEmailException(String email) {
		super("invalid email " + email);
		this.email = email;
	}

	public String getEmail() {
		return email;
	}

}
