/*
 *      Copyright (C) 2015 Noorq, Inc.
 *      All rights reserved.
 */
package com.mailrest.maildal.idgen;

import org.junit.Test;

import com.mailrest.maildal.idgen.ApiKey;

public class ApiKeyTest {

	@Test
	public void test() {
		
		String apiKey = ApiKey.next();
		
		System.out.println("apiKey = " + apiKey);
		
		
	}
	
}
