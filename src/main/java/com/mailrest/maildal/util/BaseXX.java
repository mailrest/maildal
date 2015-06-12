package com.mailrest.maildal.util;

import java.math.BigInteger;

public interface BaseXX {

	String encode(byte[] input);
	
	byte[] decode(String input);
	
	default BigInteger decodeToBigInteger(String input) {
		return new BigInteger(1, decode(input));
	}

	default String encode(BigInteger bi) {
		return encode(bi.toByteArray());
	}
}
