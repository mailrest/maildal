/*
 *      Copyright (C) 2015 Noorq, Inc.
 *      All rights reserved.
 */
package com.mailrest.maildal.gen;

import org.junit.Test;

public class PasswordGenTest {

	@Test
	public void test() {
		
		String pwd = Generators.PASSWORD.next();
		
		System.out.println("pwd = " + pwd);
		
		
	}
	
}
