/*
 *      Copyright (C) 2015 Noorq, Inc.
 *
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 */
package com.mailrest.maildal.util;

import java.math.BigInteger;
import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;

public class Base64Test {

	@Test
	public void tesString() {

		byte[] hello = "hello".getBytes();
		
		String encoded = Base64.INSTANCE.encode(hello);
		
		System.out.println("Base64 encoded = " + encoded);

		byte[] actual = Base64.INSTANCE.decode(encoded);
		
		Assert.assertTrue(Arrays.equals(hello, actual));
	}
	
	@Test
	public void testEmpty() {
		
		byte[] blob = new byte[0];
		
		String actual = Base64.INSTANCE.encode(blob);

		Assert.assertEquals(0, actual.length());
		
		byte[] a = Base64.INSTANCE.decode(actual);
		
		Assert.assertEquals(0, a.length);
		
	}
	
	@Test
	public void testZeros() {
		
		for (int i = 1; i != 10; ++i) {
		
			byte[] blob = new byte[i];
			
			String actual = Base64.INSTANCE.encode(blob);
			
			byte[] a = Base64.INSTANCE.decode(actual);
			
			Assert.assertTrue(Arrays.equals(blob, a));
		
		}
		
	}
	
	@Test
	public void testZeroBI() {
		
		String actual =  Base64.INSTANCE.encode(BigInteger.ZERO);
		
		BigInteger a = Base64.INSTANCE.decodeToBigInteger(actual);

		Assert.assertEquals(BigInteger.ZERO, a);
		
		
	}
	
}
