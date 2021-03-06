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

import java.util.List;

import com.mailrest.maildal.model.Message;
import com.mailrest.maildal.model.MessageHeaders;
import com.mailrest.maildal.model.MessageRecipient;

public final class MessageToHeadersAdapter implements MessageHeaders {

	private final Message message;
	
	public MessageToHeadersAdapter(Message message) {
		this.message = message;
	}

	@Override
	public MessageRecipient fromRecipient() {
		return message.fromRecipient();
	}

	@Override
	public List<MessageRecipient> toRecipients() {
		return message.toRecipients();
	}

	@Override
	public List<MessageRecipient> ccRecipients() {
		return message.ccRecipients();
	}

	@Override
	public List<MessageRecipient> bccRecipients() {
		return message.bccRecipients();
	}

	@Override
	public String subject() {
		return message.subject();
	}
	
}
