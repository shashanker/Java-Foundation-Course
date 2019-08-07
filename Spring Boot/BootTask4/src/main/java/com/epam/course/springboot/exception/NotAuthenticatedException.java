package com.epam.course.springboot.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * 
 * @author Shashanker_Vaduka
 *
 */
public class NotAuthenticatedException extends AuthenticationException {

	/**
	 * Reference to serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * @param msg
	 * @param t
	 */
	public NotAuthenticatedException(String msg, Throwable t) {
		super(msg, t);
	}

	/**
	 * @param msg
	 */
	public NotAuthenticatedException(String msg) {
		super(msg);
	}

}
