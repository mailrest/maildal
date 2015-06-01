/*
 *      Copyright (C) 2015 Noorq, Inc.
 *      All rights reserved.
 */
package com.mailrest.maildal.util;

import org.junit.Test;

public class ApiKeyTest {

	@Test
	public void test() {
		
		String apiKey = ApiKey.next();
		
		System.out.println("apiKey = " + apiKey);
		
		
	}
	
}
