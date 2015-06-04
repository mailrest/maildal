/*
 *      Copyright (C) 2015 Noorq, Inc.
 *      All rights reserved.
 */
package com.mailrest.maildal.secur;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.codec.binary.Base64;

import com.mailrest.maildal.gen.PasswordSalt;

public final class PasswordHash {

	public static final String DELIMITER = ":";
	
	private final static MessageDigest sha;
	
	static {
		 try {
			  sha = MessageDigest.getInstance("SHA-256");
		} catch (NoSuchAlgorithmException e) {
			  throw new IllegalStateException("SHA-256 algorithm not found", e);
		}
	}
	
	public static String calculate(String password) {
		
		String salt = PasswordSalt.next();
		
		byte[] hash = hashIt(salt + password);
		
		return salt + DELIMITER + Base64.encodeBase64String(hash);
		
	}
	
	public static boolean isEquals(String passwordHash, String password) {
		
		int index = passwordHash.indexOf(DELIMITER);
		
		if (index == -1) {
			throw new IllegalArgumentException("DELIMITER is not found in passwordHash");
		}
		
		String salt = passwordHash.substring(0, index);
		String hashBase64 = passwordHash.substring(index+1);
		
		byte[] hash = hashIt(salt + password);
		String checkHash = Base64.encodeBase64String(hash);
		
		return hashBase64.equals(checkHash);
		
	}

	private static byte[] hashIt(String source) {
		sha.reset();
		return sha.digest(source.getBytes());
	}
	
}
