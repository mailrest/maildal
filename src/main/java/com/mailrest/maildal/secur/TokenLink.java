/*
 *      Copyright (C) 2015 Noorq, Inc.
 *      All rights reserved.
 */
package com.mailrest.maildal.secur;

public enum TokenLink {
	
	INSTANCE;

	public static final class Constants {
		
		public static char DELIMITER = '.';
		public static char ESCAPE = '-';
		
		public static char ESCAPED_DELIMITER = 'D';
		public static char ESCAPED_ESCAPE = 'E';
		
	}
	
	public String encode(CharSequence jwt) {
		StringBuilder str = new StringBuilder();
		
		final int len = jwt.length();
		
		for (int i = 0; i != len; ++i) {
			
			char ch = jwt.charAt(i);
			
			if (ch == Constants.DELIMITER) {
				str.append(Constants.ESCAPE);
				str.append(Constants.ESCAPED_DELIMITER);
			}
			else if (ch == Constants.ESCAPE) {
				str.append(Constants.ESCAPE);
				str.append(Constants.ESCAPED_ESCAPE);
			}
			else {
				str.append(ch);
			}
		}
		
		return str.toString();
	}

	public String decode(CharSequence link) {
		
		StringBuilder str = new StringBuilder();
		
		final int len = link.length();
		
		boolean escapeMode = false;
			
		for (int i = 0; i < len; ++i) {
			
			char ch = link.charAt(i);

			if (escapeMode) {
				
				if (ch == Constants.ESCAPED_DELIMITER) {
					str.append(Constants.DELIMITER);
				}
				else if (ch == Constants.ESCAPED_ESCAPE) {
					str.append(Constants.ESCAPE);
				}
				else {
					str.append(Constants.ESCAPE);
					str.append(ch);
				}
				
				escapeMode = false;
			}
			else {
				
				if (ch == Constants.ESCAPE) {
					escapeMode = true;
				}
				else {
					str.append(ch);
				}
				
			}

		}
		
		return str.toString();
	}
	
}
