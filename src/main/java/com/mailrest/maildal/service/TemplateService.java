package com.mailrest.maildal.service;


public interface TemplateService extends AbstractService {
	
	default int createTemplate() {
		return 0;
	}
	
}
