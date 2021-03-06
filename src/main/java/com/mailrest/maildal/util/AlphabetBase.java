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

import java.io.UnsupportedEncodingException;
import java.util.Arrays;

public interface AlphabetBase extends BaseXX {

	public char[] alphabet();
	
	public int[] index();

	default int[] makeIndex(char[] alphabet) {
		int[] idx = new int[128];
		Arrays.fill(idx, -1);
		for (int i = 0; i < alphabet.length; i++) {
			idx[alphabet[i]] = i;
		}
		return idx;
	}

	/**
	 * Encodes bytes to some base using specific alphabet
	 * 
	 * @param input - bytes for encoding
	 * @return encoded string
	 */
	default String encode(byte[] input) {
		if (input.length == 0) {
			return "";
		}
		input = Arrays.copyOfRange(input, 0, input.length);
		
		int zeros = 0;
		while (zeros < input.length && input[zeros] == 0) {
			zeros++;
		}
		
		char[] alphabet = alphabet();
		int base = alphabet.length;
		
		// Do encoding
		byte[] temp = new byte[input.length * ((256 / base) + 1)];
		int j = temp.length;

		int startAt = zeros;
		while (startAt < input.length) {
			byte mod = divmodBase(input, startAt, base);
			if (input[startAt] == 0) {
				++startAt;
			}
			temp[--j] = (byte) alphabet[mod];
		}

		while (j < temp.length && temp[j] == alphabet[0]) {
			++j;
		}

		while (--zeros >= 0) {
			temp[--j] = (byte) alphabet[0];
		}

		byte[] output = Arrays.copyOfRange(temp, j, temp.length);
		try {
			return new String(output, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}
	}

	default byte[] decode(String input) {
		if (input.length() == 0) {
			return new byte[0];
		}
		
		int base = alphabet().length;
		int[] index = index();
		
		byte[] inputBase = new byte[input.length()];

		for (int i = 0; i < input.length(); ++i) {
			char c = input.charAt(i);

			int digitBase = -1;
			if (c >= 0 && c < 128) {
				digitBase = index[c];
			}
			if (digitBase < 0) {
				throw new IllegalArgumentException("Illegal character " + c
						+ " at " + i);
			}

			inputBase[i] = (byte) digitBase;
		}

		int zeros = 0;
		while (zeros < inputBase.length && inputBase[zeros] == 0) {
			++zeros;
		}
		
		// Do encoding
		byte[] temp = new byte[input.length()];
		int j = temp.length;

		int startAt = zeros;
		while (startAt < inputBase.length) {
			byte mod = divmod256(inputBase, startAt, base);
			if (inputBase[startAt] == 0) {
				++startAt;
			}

			temp[--j] = mod;
		}

		while (j < temp.length && temp[j] == 0) {
			++j;
		}

		return Arrays.copyOfRange(temp, j - zeros, temp.length);
	}
	
	/**
	 * 
	 * @param number is the 256 base number stored in byte array
	 * @param startAt offset in byte array where number is starting
	 * @param base is the mode of the calculation
	 * @return number % base, modifies number by number / base
	 */
	
	default byte divmodBase(byte[] number, int startAt, int base) {
		int remainder = 0;
		for (int i = startAt; i < number.length; i++) {
			int digit256 = (int) number[i] & 0xFF;
			int temp = remainder * 256 + digit256;

			number[i] = (byte) (temp / base);

			remainder = temp % base;
		}

		return (byte) remainder;
	}

	/**
	 * 
	 * @param numberBase is the number's byte array
	 * @param startAt offset in byte array where number is starting
	 * @param base is the mode of the calculation
	 * @return number % 256, modifies number by number / 256
	 */
	
	default byte divmod256(byte[] numberBase, int startAt, int base) {
		int remainder = 0;
		for (int i = startAt; i < numberBase.length; i++) {
			int digitBase = (int) numberBase[i] & 0xFF;
			int temp = remainder * base + digitBase;

			numberBase[i] = (byte) (temp / 256);

			remainder = temp % 256;
		}

		return (byte) remainder;
	}
	
}
