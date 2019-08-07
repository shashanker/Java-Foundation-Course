package com.epam.course.springboot.config;


import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class LoggerInterceptor  extends HandlerInterceptorAdapter {
	// add implementation here
	private static Logger log = Logger.getLogger(LoggerInterceptor.class.getName());
	
	@Override
	public boolean preHandle(
	  HttpServletRequest request,
	  HttpServletResponse response, 
	  Object handler) throws Exception {
	     
	    log.info("[preHandle][" + request + "]" + "[" + request.getMethod()
	      + "]" + request.getRequestURI() );
	     
	    return true;
	}
	
	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		log.info("[postHandle][" + request + "]" + "[" + request.getMethod()
	      + "]" + request.getRequestURI() );
		super.postHandle(request, response, handler, modelAndView);
	}
	
}
