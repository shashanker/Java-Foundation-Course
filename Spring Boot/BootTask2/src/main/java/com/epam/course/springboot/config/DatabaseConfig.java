package com.epam.course.springboot.config;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableJpaRepositories("com.epam.course.springboot.repository")
@EnableTransactionManagement
public class DatabaseConfig {

}