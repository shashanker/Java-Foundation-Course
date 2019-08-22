package com.epam.course.hibernate.config;

import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableJpaRepositories("com.epam.course.hibernate.service")
@EnableTransactionManagement
public class DatabaseConfig {

}
