package com.epam.course.springboot.config;
import java.util.HashSet;
import java.util.Set;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ConversionServiceFactoryBean;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;

import converter.AgentEntityToAgentResponseConverter;
import converter.AgentRequestToAgentEntityConverter;
import converter.PropertyEntityToPropertyResponseConverter;
import converter.PropertyRequestToPropertyEntityConverter;

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
		converters.add(new PropertyRequestToPropertyEntityConverter());
		converters.add(new PropertyEntityToPropertyResponseConverter());
		converters.add(new AgentEntityToAgentResponseConverter());
		converters.add(new AgentRequestToAgentEntityConverter());
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
