/*
 *      Copyright (C) 2015 Noorq, Inc.
 *      All rights reserved.
 */
package com.mailrest.maildal.util;

import java.net.IDN;

public enum DomainId {

	INSTANCE;
	
	public String fromDomainIdn(String domainIdn) {
		return IDN.toASCII(domainIdn.toLowerCase());
	}
	
}
