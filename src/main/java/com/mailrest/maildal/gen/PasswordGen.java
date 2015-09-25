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
package com.mailrest.maildal.gen;

import com.mailrest.maildal.util.Base58;
import com.mailrest.maildal.util.RandomInstance;

public final class PasswordGen implements SimpleGenerator {

	private final static int KEY_LENGTH = 80 / 8;
	
	PasswordGen() {
	}
	
	public String next() {
		return Base58.INSTANCE.encode(rnd(KEY_LENGTH));
	}
	
	private static byte[] rnd(int length) {
		return RandomInstance.INSTANCE.nextBytes(length);
	}
}
