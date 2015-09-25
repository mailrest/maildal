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

public class Base58Test {

	@Test
	public void testLength() {

		Assert.assertEquals(58, Base58.INSTANCE.alphabet().length);

	}

	@Test
	public void testOne() {

		for (int i = 0; i != Base58.INSTANCE.alphabet().length; ++i) {

			byte[] blob = new byte[1];
			blob[0] = (byte) i;

			String encoded = Base58.INSTANCE.encode(blob);
			Assert.assertEquals("" + Base58.INSTANCE.alphabet()[i], encoded);

			byte[] actual = Base58.INSTANCE.decode(encoded);

			Assert.assertTrue(Arrays.equals(blob, actual));
		}

	}

	@Test
	public void testOneBI() {

		for (int i = 0; i != Base58.INSTANCE.alphabet().length; ++i) {

			BigInteger bi = BigInteger.valueOf(i);
			
			String encoded = Base58.INSTANCE.encode(bi);
			Assert.assertEquals("" + Base58.INSTANCE.alphabet()[i], encoded);

			BigInteger actual = Base58.INSTANCE.decodeToBigInteger(encoded);

			Assert.assertEquals(bi, actual);
		}

	}
	
	@Test
	public void testTwo() {

		for (int i = 0; i != Base58.INSTANCE.alphabet().length; ++i) {
			for (int j = 0; j != Base58.INSTANCE.alphabet().length; ++j) {
				
				String encoded = "" + Base58.INSTANCE.alphabet()[i] + Base58.INSTANCE.alphabet()[j];
				
				byte[] blob = Base58.INSTANCE.decode(encoded);
				
				String actual = Base58.INSTANCE.encode(blob);
		
				
				Assert.assertEquals(encoded, actual);
				
			}			
			
		}
		
	}
	
	@Test
	public void testEmpty() {
		
		byte[] blob = new byte[0];
		
		String actual = Base58.INSTANCE.encode(blob);

		Assert.assertEquals(0, actual.length());
		
		byte[] a = Base58.INSTANCE.decode(actual);
		
		Assert.assertEquals(0, a.length);
		
	}
	
	@Test
	public void testZeros() {
		
		for (int i = 1; i != 10; ++i) {
		
			byte[] blob = new byte[i];
			
			String actual = Base58.INSTANCE.encode(blob);
			
			byte[] a = Base58.INSTANCE.decode(actual);
			
			Assert.assertTrue(Arrays.equals(blob, a));
		
		}
		
	}
	
	@Test
	public void testZeroBI() {
		
		String actual =  Base58.INSTANCE.encode(BigInteger.ZERO);
		
		BigInteger a = Base58.INSTANCE.decodeToBigInteger(actual);

		Assert.assertEquals(BigInteger.ZERO, a);
		
		
	}
	
}
