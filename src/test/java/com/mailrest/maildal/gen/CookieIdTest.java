/*
 *      Copyright (C) 2015 Noorq, Inc.
 *      All rights reserved.
 */
package com.mailrest.maildal.gen;

import org.junit.Test;

public class CookieIdTest {

	@Test
	public void test() {
		
		String cid = Generators.COOKIE_ID.next();
		
		System.out.println("cid = " + cid);
		
		
	}
	
}
