/*
 *      Copyright (C) 2015 Noorq, Inc.
 *      All rights reserved.
 */
package com.mailrest.maildal.gen;

import org.junit.Test;

public class PasswordSaltTest {

	@Test
	public void test() {
		
		String salt = Generators.PASSWORD_SALT.next();
		
		System.out.println("salt = " + salt);
		
		
	}
	
}
