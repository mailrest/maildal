/*
 *      Copyright (C) 2015 Noorq, Inc.
 *      All rights reserved.
 */
package com.mailrest.maildal.gen;

import org.junit.Test;

import com.mailrest.maildal.gen.ApiKey;

public class ApiKeyTest {

	@Test
	public void test() {
		
		String apiKey = ApiKey.next();
		
		System.out.println("apiKey = " + apiKey);
		
		
	}
	
}
