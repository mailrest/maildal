/*
 *      Copyright (C) 2015 Noorq, Inc.
 *      All rights reserved.
 */
package com.mailrest.maildal.util;

import java.math.BigInteger;
import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;

public class Base62Test {

	@Test
	public void testLength() {

		Assert.assertEquals(62, Base62.INSTANCE.alphabet().length);

	}

	@Test
	public void testOne() {

		for (int i = 0; i != Base62.INSTANCE.alphabet().length; ++i) {

			byte[] blob = new byte[1];
			blob[0] = (byte) i;

			String encoded = Base62.INSTANCE.encode(blob);
			Assert.assertEquals("" + Base62.INSTANCE.alphabet()[i], encoded);

			byte[] actual = Base62.INSTANCE.decode(encoded);

			Assert.assertTrue(Arrays.equals(blob, actual));
		}

	}

	@Test
	public void testOneBI() {

		for (int i = 0; i != Base62.INSTANCE.alphabet().length; ++i) {

			BigInteger bi = BigInteger.valueOf(i);
			
			String encoded = Base62.INSTANCE.encode(bi);
			Assert.assertEquals("" + Base62.INSTANCE.alphabet()[i], encoded);

			BigInteger actual = Base62.INSTANCE.decodeToBigInteger(encoded);

			Assert.assertEquals(bi, actual);
		}

	}
	
	@Test
	public void testTwo() {

		for (int i = 0; i != Base62.INSTANCE.alphabet().length; ++i) {
			for (int j = 0; j != Base62.INSTANCE.alphabet().length; ++j) {
				
				String encoded = "" + Base62.INSTANCE.alphabet()[i] + Base62.INSTANCE.alphabet()[j];
				
				byte[] blob = Base62.INSTANCE.decode(encoded);
				
				String actual = Base62.INSTANCE.encode(blob);
		
				
				Assert.assertEquals(encoded, actual);
				
			}			
			
		}
		
	}
	
	@Test
	public void testEmpty() {
		
		byte[] blob = new byte[0];
		
		String actual = Base62.INSTANCE.encode(blob);

		Assert.assertEquals(0, actual.length());
		
		byte[] a = Base62.INSTANCE.decode(actual);
		
		Assert.assertEquals(0, a.length);
		
	}
	
	@Test
	public void testZeros() {
		
		for (int i = 1; i != 10; ++i) {
		
			byte[] blob = new byte[i];
			
			String actual = Base62.INSTANCE.encode(blob);
			
			byte[] a = Base62.INSTANCE.decode(actual);
			
			Assert.assertTrue(Arrays.equals(blob, a));
		
		}
		
	}
	
	@Test
	public void testZeroBI() {
		
		String actual =  Base62.INSTANCE.encode(BigInteger.ZERO);
		
		BigInteger a = Base62.INSTANCE.decodeToBigInteger(actual);

		Assert.assertEquals(BigInteger.ZERO, a);
		
		
	}
	
}
