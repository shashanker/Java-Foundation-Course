package com.epam.course.springcore.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.epam.course.springcore.domain.Position;
import com.epam.course.springcore.service.EmployeeService;
import com.epam.course.springcore.service.PositionService;
import com.epam.course.springcore.service.SalaryService;
import com.epam.course.springcore.util.Company;
import com.epam.course.springcore.util.Util;

@Configuration
@ComponentScan(basePackages="com.epam.course.springcore")
public class AppConfig {
	
	
	@Bean(name="empServ")
	public EmployeeService empServie() {
		return new EmployeeService();
	}
	
	@Bean
	public PositionService positionService() {
		return new PositionService();
	}
	
	@Bean
	public SalaryService salaryService() {
		return new SalaryService();
	}
	
	
	@Bean
	public Company company() {
		return new Company();
	}
}
