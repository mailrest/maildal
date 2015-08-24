/*
 *      Copyright (C) 2015 Noorq, Inc.
 *      All rights reserved.
 */
package com.mailrest.maildal.secur;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Arrays;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

import com.mailrest.maildal.util.Base64;
import com.mailrest.maildal.util.RandomInstance;

public enum PasswordHash {

	INSTANCE;
	
	public static final String DEFAULT_VERSION = "1";
	
	public static final String ALGORITHM = "PBKDF2WithHmacSHA1";
	
	public static final String DELIMITER = ":";
	
	public String calculate(String password) {
		
		byte[] salt = new byte[16];
		RandomInstance.INSTANCE.random().nextBytes(salt);
		
		byte[] hash = doCalculate(salt, password);
		
		return DEFAULT_VERSION + Base64.INSTANCE.encode(salt) + DELIMITER + Base64.INSTANCE.encode(hash);
		
	}
	
	public boolean isEquals(String passwordHash, String password) {
		
		if (!passwordHash.startsWith(DEFAULT_VERSION)) {
			throw new SecurityException("unsupported version of the passwordHash " + passwordHash);
		}
		
		int index = passwordHash.indexOf(DELIMITER, 1);
		
		if (index == -1) {
			throw new SecurityException("DELIMITER not found in passwordHash");
		}
		
		byte[] salt = Base64.INSTANCE.decode(passwordHash.substring(1, index));
		byte[] storedHash = Base64.INSTANCE.decode(passwordHash.substring(index+1));
		
		
		byte[] hash = doCalculate(salt, password);
		
		return Arrays.equals(storedHash, hash);
		
	}

	private byte[] doCalculate(byte[] salt, String password) {
		
		PBEKeySpec spec = new PBEKeySpec(password.toCharArray(), salt, 1, 64 * 8);
		SecretKeyFactory skf;
		try {
		    skf = SecretKeyFactory.getInstance(ALGORITHM);
		} catch (NoSuchAlgorithmException e) {
			throw new SecurityException("password hashing algorithm not found " + ALGORITHM, e);
		}
		byte[] hash;
		try {
			hash = skf.generateSecret(spec).getEncoded();
		} catch (InvalidKeySpecException e) {
			throw new SecurityException("invalid key generated for " + ALGORITHM, e);
		}
		
		return hash;
		
	}
	
}
