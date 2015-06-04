/*
 *      Copyright (C) 2015 Noorq, Inc.
 *      All rights reserved.
 */
package com.mailrest.maildal.gen;

import org.junit.Test;

public class RequestIdTest {

	@Test
	public void test() {
		
		String rid = RequestId.next();
		
		System.out.println("rid = " + rid);
		
		
	}
	
}
