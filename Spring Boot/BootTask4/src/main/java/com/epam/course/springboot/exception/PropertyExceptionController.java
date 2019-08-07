package com.epam.course.springboot.exception;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import com.epam.course.springboot.config.LoggerInterceptor;

@ControllerAdvice
public class PropertyExceptionController {
	private static Logger log = Logger.getLogger(LoggerInterceptor.class.getName());
	@ExceptionHandler(value = PropertyNotfoundException.class)
	public ResponseEntity<Object> exception(Exception exception) {
		return new ResponseEntity<>("Property not found", HttpStatus.NOT_FOUND);
	}
	
	 @ExceptionHandler(Error404.class)
	    public ModelAndView handleError404(HttpServletRequest request, Exception e)   {
	        Logger.getLogger(getClass().getName()).log(Level.SEVERE, "Request: " + request.getRequestURL() + " raised " + e);
	        return new ModelAndView("404");
	    }
}
