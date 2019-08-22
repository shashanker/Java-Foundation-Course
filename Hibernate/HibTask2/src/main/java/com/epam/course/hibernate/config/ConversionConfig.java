package com.epam.course.hibernate.config;

import java.util.HashSet;
import java.util.Set;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ConversionServiceFactoryBean;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;

import converter.EmployeeEntityToEmployeeResponseConverter;
import converter.EmployeeRequestToEmployeeEntityConverter;
import converter.ProjectEntityToProjectResponseConverter;
import converter.ProjectRequestToProjectEntityConverter;
import converter.UnitEntityToUnitResponseConverter;
import converter.UnitRequestToUnitEntityConverter;

/**
 * 
 * @author Shashanker_Vaduka
 *
 *         Conversion config will register converters that will allow us to
 *         convert request or response objects into entities and vice versa
 */
@Configuration
public class ConversionConfig {

	private Set<Converter> getConverters() {
		Set<Converter> converters = new HashSet<>();
		converters.add(new EmployeeEntityToEmployeeResponseConverter());
		converters.add(new EmployeeRequestToEmployeeEntityConverter());
		converters.add(new ProjectRequestToProjectEntityConverter());
		converters.add(new ProjectEntityToProjectResponseConverter());
		converters.add(new UnitRequestToUnitEntityConverter());
		converters.add(new UnitEntityToUnitResponseConverter());
		return converters;
	}
	
	@Bean
	public ConversionService conversionService() {
		ConversionServiceFactoryBean bean = new ConversionServiceFactoryBean();
		bean.setConverters(getConverters());
		bean.afterPropertiesSet();
		return bean.getObject();
	}
}