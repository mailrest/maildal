/*
 *      Copyright (C) 2015 Noorq, Inc.
 *      All rights reserved.
 */
package com.mailrest.maildal.secur;

public class InvalidTokenException extends RuntimeException {

	private static final long serialVersionUID = -7193833356479827713L;

	public InvalidTokenException(String msg) {
		super(msg);
	}

	public InvalidTokenException(Throwable e) {
		super(e);
	}

	public InvalidTokenException(String msg, Throwable e) {
		super(msg, e);
	}

}
