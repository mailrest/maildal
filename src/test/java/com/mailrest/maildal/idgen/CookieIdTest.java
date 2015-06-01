/*
 *      Copyright (C) 2015 Noorq, Inc.
 *      All rights reserved.
 */
package com.mailrest.maildal.idgen;

import org.junit.Test;

import com.mailrest.maildal.idgen.CookieId;

public class CookieIdTest {

	@Test
	public void test() {
		
		String cid = CookieId.next();
		
		System.out.println("cid = " + cid);
		
		
	}
	
}
