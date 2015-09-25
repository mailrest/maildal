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
