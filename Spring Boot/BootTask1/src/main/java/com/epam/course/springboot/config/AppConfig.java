package com.epam.course.springboot.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.config.ServiceLocatorFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.epam.course.springboot.registry.CompanyRegistry;
import com.epam.course.springboot.service.EmployeeService;
import com.epam.course.springboot.service.PositionService;
import com.epam.course.springboot.service.SalaryService;
import com.epam.course.springcore.util.Company;

@Configuration
public class AppConfig {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Bean
	public FactoryBean<?> getBean(){
		logger.debug("inside factory bean");
		ServiceLocatorFactoryBean serviceLocatorFactoryBean = new ServiceLocatorFactoryBean();
		serviceLocatorFactoryBean.setServiceLocatorInterface(CompanyRegistry.class);
		return serviceLocatorFactoryBean;
	}
	
	/*
	 * Autowired byName
	 */
	@Profile("release")
	@Bean(name = "empServ")
	public EmployeeService empReleaseServie() {
		logger.debug("EmployeeService Bean Created with 'release' profile");
		return new EmployeeService("release");
	}
	
	@Profile("debug")
	@Bean(name = "empServ")
	public EmployeeService empDebugServie() {
		logger.debug("EmployeeService Bean Created with 'debug' profile");
		return new EmployeeService("debug");
	}
	/*
	 * Bean using Factory-Method , AutoWire byType
	 */
	@Bean
	public PositionService positionService() {
		logger.debug("PositionService Bean Created");
		return PositionService.getPositionService();
	}

	@Bean
	public SalaryService salaryService() {
		logger.debug("SalaryService Bean Created");
		return new SalaryService();
	}

	@Bean
	public Company company() {
		logger.debug("Company Bean Created");
		return new Company();
	}
}
