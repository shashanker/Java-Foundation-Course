package com.epam.course.springmvc.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
@PropertySource("classpath:application.properties")
public class DatabaseConfig {

	@Autowired
	private Environment env;
	
	@Bean
	public DataSource dataSource() {
		 DriverManagerDataSource dataSource = new DriverManagerDataSource();
	       
	        //H2 database
	        
	       /* dataSource.setDriverClassName("org.h2.Driver");
	        dataSource.setUrl("jdbc:h2:tcp://localhost/~/test");
	        dataSource.setUsername("sa");
	        dataSource.setPassword("");*/
	        
	        dataSource.setDriverClassName(env.getProperty("spring.datasource.driver"));
			dataSource.setUrl(env.getProperty("spring.datasource.jdbc-url"));
			dataSource.setUsername("sa");
			dataSource.setPassword("");
	        return dataSource;
	}
	
	
}
