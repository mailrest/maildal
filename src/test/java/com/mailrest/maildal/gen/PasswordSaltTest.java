/*
 *      Copyright (C) 2015 Noorq, Inc.
 *      All rights reserved.
 */
package com.mailrest.maildal.gen;

import org.junit.Test;

import com.mailrest.maildal.gen.PasswordSalt;

public class PasswordSaltTest {

	@Test
	public void test() {
		
		String salt = PasswordSalt.next();
		
		System.out.println("salt = " + salt);
		
		
	}
	
}
