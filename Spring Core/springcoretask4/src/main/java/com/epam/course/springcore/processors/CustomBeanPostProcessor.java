package com.epam.course.springcore.processors;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
 
public class CustomBeanPostProcessor implements BeanPostProcessor
{
	private final static Logger log = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException
    {
        log.log(Level.INFO,"Called postProcessBeforeInitialization() for :" + beanName);
        return bean;
    }
     
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException
    {
    	log.log(Level.INFO,"Called postProcessAfterInitialization() for :" + beanName);
        return bean;
    }
}