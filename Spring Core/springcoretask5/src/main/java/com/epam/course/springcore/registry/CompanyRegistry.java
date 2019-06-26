package com.epam.course.springcore.registry;

import com.epam.course.springcore.service.CompanyService;

public interface CompanyRegistry {

	public CompanyService getServiceBean(String serviceBean);
}
