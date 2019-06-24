package com.epam.course.springcore.beans;

import java.util.logging.Logger;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
public class F  implements InitializingBean,DisposableBean{

	private final static Logger log = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
	
	F()
	{
		log.info("F : Constructor is called");
	}
	
	public void afterPropertiesSet() throws Exception {
		// TODO Auto-generated method stub
		
		log.info("F : InitializingBean's afterPropertiesSet is called ");
	}
	
	
	public void destroy() throws Exception {
		// TODO Auto-generated method stub
		
		log.info("F : DisposableBean's destroy is called");
		
	}
	
	public void customInit() {
		log.info("F : init-method is called");
	}
	
	public void customDestroy() {
		log.info("F : destroyed-method is called");
	}
	
	
	@PostConstruct
	public void postConstruct() {
		log.info("F : postConstruct is called");
	}
	
	@PreDestroy
	public void preDestroy() {
		log.info("F : preDestroy-method is called");
	}
	
}
