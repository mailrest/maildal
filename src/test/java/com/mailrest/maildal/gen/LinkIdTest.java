/*
 *      Copyright (C) 2015 Noorq, Inc.
 *      All rights reserved.
 */
package com.mailrest.maildal.gen;

import org.junit.Test;

import com.mailrest.maildal.gen.LinkId;

public class LinkIdTest {

	@Test
	public void test() {
		
		String lid = LinkId.next();
		
		System.out.println("lid = " + lid);
		
		
	}
	
}
