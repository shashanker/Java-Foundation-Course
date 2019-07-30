package com.epam.course.springmvc.config;


import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

public class MyWebAppInitializer extends 
	AbstractAnnotationConfigDispatcherServletInitializer {
	
	@Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class<?>[] { SpringRootConfig.class };
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class<?>[] { 
        		MainWebConfiguration.class
        	};
    }

    @Override
    protected String[] getServletMappings() {
        return new String[] { "/" };
    }

    /*@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		super.onStartup(servletContext);
		servletContext.setInitParameter("spring.profiles.active", "h2");
	}*/
}
