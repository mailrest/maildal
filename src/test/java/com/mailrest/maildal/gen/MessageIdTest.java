/*
 *      Copyright (C) 2015 Noorq, Inc.
 *      All rights reserved.
 */
package com.mailrest.maildal.gen;

import org.junit.Test;

public class MessageIdTest {

	@Test
	public void test() {
		
		String mid = MessageId.next("lt.su");
		
		System.out.println("mid = " + mid);
		
		
	}
	
}
