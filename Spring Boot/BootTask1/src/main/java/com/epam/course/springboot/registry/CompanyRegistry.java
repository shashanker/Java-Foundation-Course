package com.epam.course.springboot.registry;

import com.epam.course.springboot.service.CompanyService;

public interface CompanyRegistry {

	public CompanyService getServiceBean(String serviceBean);
}
