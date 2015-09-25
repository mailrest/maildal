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
