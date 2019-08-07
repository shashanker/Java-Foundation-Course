package com.epam.course.springboot;

import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import com.epam.course.springboot.registry.CompanyRegistry;
import com.epam.course.springboot.service.CompanyService;
import com.epam.course.springcore.util.Company;
import com.epam.course.springcore.util.Util;

/**
 * 
 * @author Shashanker_Vaduka
 *
 */
@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan
public class App {
	private final static Logger logger = LoggerFactory.getLogger(App.class);
	
	public static void main(String[] args) {
		logger.info("Welcome to the Company");
		try {
			ConfigurableApplicationContext context = SpringApplication
					.run(App.class, args);
			Util.initApp();
			Company company = context.getBean(Company.class);
			company.showOptions();

			/*
			 * Sample to show the factory-bean (service locator)
			 */
			
			
			logger.info("Exited the Company App services");
			System.out.println(
					"Below is sample demo for factory-bean (service locator)");
			CompanyRegistry companyRegistry = context
					.getBean(CompanyRegistry.class);
			System.out
					.println("pick one of the service \n" + "\t EmployeeService \n"
							+ "\t PositionService \n" + "\t SalaryService");
			Scanner in = new Scanner(System.in);
			String value = in.nextLine();
			CompanyService companyService = companyRegistry.getServiceBean(value);
			companyService.print();
		} catch (BeansException e) {
			// TODO Auto-generated catch block
			logger.error("Error in main() :"+ e.getMessage());
		}
	}
}
