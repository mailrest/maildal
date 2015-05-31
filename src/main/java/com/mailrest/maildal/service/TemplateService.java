package com.mailrest.maildal.service;

import com.noorq.casser.core.CasserSession;

public interface TemplateService {

	CasserSession session();
	
	default int createTemplate() {
		return 0;
	}
	
}
