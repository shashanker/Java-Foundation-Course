package com.epam.course.springmvc.config;


import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import com.epam.course.springmvc.model.Util;

@Configuration
@ComponentScan()
public class RootAppConfig {

	// @Bean configuration methods here
	
	@Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        //MySQL database we are using
        dataSource.setDriverClassName("org.h2.Driver");
       // dataSource.setUrl("jdbc:h2:tcp://localhost/~/ecommerce");//change url
        dataSource.setUrl("jdbc:h2:~/test");//change url
        dataSource.setUsername("sa");//change userid
        dataSource.setPassword("");//change pwd
        
        //H2 database
        /*
        dataSource.setDriverClassName("org.h2.Driver");
        dataSource.setUrl("jdbc:h2:tcp://localhost/~/test");
        dataSource.setUsername("sa");
        dataSource.setPassword("");*/
        return dataSource;
    }
	
	@Bean
	public Util util() {
		return new Util();
	}
	
	
}
