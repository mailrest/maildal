/*
 *      Copyright (C) 2015 Noorq, Inc.
 *      All rights reserved.
 */
package com.mailrest.maildal.util;


public enum Base64 implements BaseXX {
	
	INSTANCE;
	
	public String encode(byte[] input) {
		
		if (input.length == 0) {
			return "";
		}
		
		return org.apache.commons.codec.binary.Base64.encodeBase64URLSafeString(input);
	}
	
	public byte[] decode(String input) {
		
		if (input.length() == 0) {
			return new byte[0];
		}
		
		return org.apache.commons.codec.binary.Base64.decodeBase64(input);
	}
	
}
