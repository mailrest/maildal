/*
 *      Copyright (C) 2015 Noorq, Inc.
 *      All rights reserved.
 */
package com.mailrest.maildal.gen;

import org.junit.Test;

public class ApiKeyTest {

	@Test
	public void test() {
		
		String apiKey = Generators.API_KEY.next();
		
		System.out.println("apiKey = " + apiKey);
		
		
	}
	
}
