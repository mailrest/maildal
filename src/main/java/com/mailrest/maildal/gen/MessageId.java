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

import java.text.SimpleDateFormat;
import java.util.Date;

import com.mailrest.maildal.util.RandomInstance;

public final class MessageId implements ParametarizedGenerator {

	private static final int DIGITS_MOD = 100000;
	private final static SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
	
	MessageId() {
	}
	
	/*
	 * Message id formated, example here: 20150516120002.16302.52977@lt.su
	 */
	
	public String next(String domain) {
		
		Date dt = new Date();
		
		String prefix;
		synchronized (sdf) {
			prefix = sdf.format(dt);
		}
		
		long nanoTime = System.nanoTime() % DIGITS_MOD;
		
		int randomValue = RandomInstance.INSTANCE.nextNaturalInt(DIGITS_MOD);
		
		return prefix + "." + nanoTime + "." + randomValue + "@" + domain.toLowerCase();
		
	}
	
}
