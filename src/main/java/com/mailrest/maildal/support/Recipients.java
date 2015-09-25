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
package com.mailrest.maildal.support;

import java.util.ArrayList;
import java.util.List;

import com.mailrest.maildal.model.MessageRecipient;


public enum Recipients {

	INSTANCE;

	public List<MessageRecipient> parseMulti(String recipients, boolean ignoreErrors) {
		List<MessageRecipient> list = new ArrayList<MessageRecipient>();
		parseMulti(recipients, ignoreErrors, list);
		return list;
	}
	
	public void parseMulti(String recipients, boolean ignoreErrors, List<? super Recipient> out) {
		
		if (recipients == null) {
			
			if (ignoreErrors) {
				return;
			}
			else {
				throw new IllegalArgumentException("empty recipients");
			}
			
		}
		
		String[] arr = recipients.split(",");
		int len = arr.length;
		
		for (int i = 0; i != len; ++i) {
			
			String value = arr[i].trim();
			
			if (!value.isEmpty()) {
				
				if (ignoreErrors) {
					
					try {
						out.add(parseSingle(value));
					}
					catch(IllegalArgumentException e) {
						// ignore
					}
					
				}
				else {
					out.add(parseSingle(value));
				}
				
			}
			
		}

	}


	public Recipient parseSingle(String recipient) {
		
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
	
	private String removeQuotes(String src) {
		
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
