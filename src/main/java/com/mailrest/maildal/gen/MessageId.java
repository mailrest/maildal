/*
 *      Copyright (C) 2015 Noorq, Inc.
 *      All rights reserved.
 */
package com.mailrest.maildal.gen;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.mailrest.maildal.util.RandomInstance;

public final class MessageId {

	private static final int DIGITS_MOD = 100000;
	private final static SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
	
	private MessageId() {
	}
	
	/*
	 * Message id formated, example here: 20150516120002.16302.52977@lt.su
	 */
	
	public static String next(String domain) {
		
		Date dt = new Date();
		
		String prefix;
		synchronized (sdf) {
			prefix = sdf.format(dt);
		}
		
		long nanoTime = System.nanoTime() % DIGITS_MOD;
		
		int randomValue = Math.abs(RandomInstance.INSTANCE.random().nextInt()) % DIGITS_MOD;
		
		return prefix + "." + nanoTime + "." + randomValue + "@" + domain.toLowerCase();
		
	}
	
}
