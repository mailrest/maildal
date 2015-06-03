/*
 *      Copyright (C) 2015 Noorq, Inc.
 *      All rights reserved.
 */
package com.mailrest.maildal.gen;

import org.junit.Test;

import com.mailrest.maildal.gen.PasswordGen;

public class PasswordGenTest {

	@Test
	public void test() {
		
		String pwd = PasswordGen.next();
		
		System.out.println("pwd = " + pwd);
		
		
	}
	
}
