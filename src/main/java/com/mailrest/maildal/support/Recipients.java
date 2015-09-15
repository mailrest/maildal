/*
 *      Copyright (C) 2015 Noorq, Inc.
 *      All rights reserved.
 */
package com.mailrest.maildal.support;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;



public final class Recipients {

	private Recipients() {
	}
	
	public static List<Recipient> parseMulti(String recipients, boolean ignoreErrors) {
		
		if (recipients == null) {
			
			if (ignoreErrors) {
				return Collections.emptyList();
			}
			else {
				throw new IllegalArgumentException("empty recipients");
			}
			
		}
		
		String[] arr = recipients.split(",");
		int len = arr.length;
		
		List<Recipient> list = new ArrayList<Recipient>(len);
		
		for (int i = 0; i != len; ++i) {
			
			String value = arr[i].trim();
			
			if (!value.isEmpty()) {
				
				if (ignoreErrors) {
					
					try {
						list.add(parseSingle(value));
					}
					catch(IllegalArgumentException e) {
						// ignore
					}
					
				}
				else {
					list.add(parseSingle(value));
				}
				
			}
			
		}
		
		return list;
	}


	public static Recipient parseSingle(String recipient) {
		
		if (recipient == null) {
			throw new IllegalArgumentException("empty recipient");
		}
		
		int addressIdx = recipient.indexOf('<');
		
		if (addressIdx != -1) {
			
			
			int endAddressIdx = recipient.indexOf('>', addressIdx);
			
			if (endAddressIdx == -1) {
				endAddressIdx = recipient.length();
			}
			
			String name = recipient.substring(0, addressIdx).trim();
			String address = recipient.substring(Math.min(addressIdx+1, endAddressIdx), endAddressIdx).trim();

			name = removeQuotes(name);
			
			return new Recipient(name, address);
		}
		else {
			return new Recipient(null, recipient.trim());
		}
		
	}
	
	private static String removeQuotes(String src) {
		
		while(!src.isEmpty()) {
			
			if (src.charAt(0) == '\'') {
				
				int len = src.length();
				if (src.charAt(len-1) == '\'') {
					src = src.substring(1, len-1).trim();
				}
				else {
					src = src.substring(1, len).trim();
				}
				
			}
			else if (src.charAt(0) == '\"') {
				
				int len = src.length();
				if (src.charAt(len-1) == '\"') {
					src = src.substring(1, len-1).trim();
				}
				else {
					src = src.substring(1, len).trim();
				}
				
			}
			else {
				break;
			}
			
		}

		return src;
	}
	
}
