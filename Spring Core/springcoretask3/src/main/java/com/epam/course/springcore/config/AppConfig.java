package com.epam.course.springcore.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import com.epam.course.springcore.service.EmulationService;
import com.epam.course.springcore.service.HorseService;
import com.epam.course.springcore.service.RaceService;

@ComponentScan(basePackages="com.epam.course.springcore")
@Configuration
public class AppConfig {
	
	@Bean
	public RaceService raceService()
	{
		return new RaceService();
	}
	
	@Bean
	public HorseService horseService()
	{
		return new HorseService();
	}
	
	@Scope("prototype")
	@Bean
	public EmulationService emulationService()
	{
		return new EmulationService();
	}

}
