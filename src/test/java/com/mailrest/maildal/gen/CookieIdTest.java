/*
 *      Copyright (C) 2015 Noorq, Inc.
 *      All rights reserved.
 */
package com.mailrest.maildal.gen;

import org.junit.Test;

import com.mailrest.maildal.gen.CookieId;

public class CookieIdTest {

	@Test
	public void test() {
		
		String cid = CookieId.next();
		
		System.out.println("cid = " + cid);
		
		
	}
	
}
