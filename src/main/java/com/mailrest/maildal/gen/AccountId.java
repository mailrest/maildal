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

import com.mailrest.maildal.util.RandomInstance;


public final class AccountId implements SimpleGenerator {

	public static final int ACCOUNT_MOD = 100000;
	
	AccountId() {
	}

	@Override
	public String next() {
		return "1" + Integer.toString(nextInt()) + Integer.toString(nextInt());
	}
	
	private static int nextInt() {
		return RandomInstance.INSTANCE.nextNaturalInt(ACCOUNT_MOD);
	}
	
}
