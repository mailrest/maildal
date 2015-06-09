/*
 *      Copyright (C) 2015 Noorq, Inc.
 *      All rights reserved.
 */
package com.mailrest.maildal.gen;

import org.junit.Test;

public class LinkIdTest {

	@Test
	public void test() {
		
		String lid = Generators.LINK_ID.next();
		
		System.out.println("lid = " + lid);
		
		
	}
	
}
